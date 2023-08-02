package com.vivek.dilyquiz.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.vivek.dilyquiz.R
import com.vivek.dilyquiz.adapter.QuizAdapter
import com.vivek.dilyquiz.models.Quiz
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var toolBar:androidx.appcompat.widget.Toolbar
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var quizRecyclerView: RecyclerView
    lateinit var firestore: FirebaseFirestore
    lateinit var btnDatePicker:FloatingActionButton
    lateinit var navigationView: NavigationView
    lateinit var mainDrawer:DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolBar = findViewById(R.id.toolbar)
        mainDrawer = findViewById(R.id.mainDrawer)
        quizRecyclerView = findViewById(R.id.quizRecyclerView)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        navigationView = findViewById(R.id.navigationView)

       // populateDummyData()
        setUpViews()
    }
    fun setUpViews(){
        setUpFireStore()
        setUpDrawerLayout()
        setUpReclerView()
        setUpDatePicker()
    }


    fun setUpDrawerLayout(){

        setSupportActionBar(toolBar)

   // MAKING HUMBERURGER FUNCTIONING
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        supportActionBar?.title = "Daily Quiz"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            val intent = Intent(this,ProfileActivity::class.java)
            startActivity(intent)
            mainDrawer.closeDrawers()
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpReclerView() {
        adapter = QuizAdapter(this,quizList)
        quizRecyclerView.layoutManager = GridLayoutManager(this,2)
        quizRecyclerView.adapter = adapter

    }

    fun populateDummyData(){
        quizList.add(Quiz("26/07/2023","26/07/2023"))
        quizList.add(Quiz("27/07/2023","27/07/2023"))
        quizList.add(Quiz("28/07/2023","28/07/2023"))
        quizList.add(Quiz("29/07/2023","29/07/2023"))
        quizList.add(Quiz("30/07/2023","30/07/2023"))
        quizList.add(Quiz("31/07/2023","31/07/2023"))

    }

    private fun setUpFireStore() {
        firestore = FirebaseFirestore.getInstance()
        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this,"Error Fetching data",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
            quizList.clear()
            quizList.addAll(value.toObjects(Quiz::class.java))
            adapter.notifyDataSetChanged()


        }
    }

    fun setUpDatePicker(){
        btnDatePicker.setOnClickListener{
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager,"DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)

                val dateFormatter = SimpleDateFormat("dd/mm/yyyy")
                val date = dateFormatter.format(Date(it))

                val intent = Intent(this,QuestionActivity::class.java)
                intent.putExtra("DATE",date)
                startActivity(intent)



            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER",datePicker.headerText)
            }

            datePicker.addOnCancelListener {
                Log.d("DATEPICKER","Date Picker Cancel")
            }
        }
    }
}