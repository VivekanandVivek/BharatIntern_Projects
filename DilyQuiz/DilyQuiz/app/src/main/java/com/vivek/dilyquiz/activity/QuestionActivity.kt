package com.vivek.dilyquiz.activity

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.vivek.dilyquiz.R
import com.vivek.dilyquiz.adapter.OptionAdapter
import com.vivek.dilyquiz.models.Questions
import com.vivek.dilyquiz.models.Quiz

class QuestionActivity : AppCompatActivity() {

    lateinit var description:TextView
    lateinit var optionList:RecyclerView
    lateinit var btnPrevious:Button
    lateinit var btnNext:Button
    lateinit var btnSubmit:Button

    var quizzes:MutableList<Quiz>? = null
    var questions:MutableMap<String,Questions>? = null
    var index = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)


        description = findViewById(R.id.description)
        optionList = findViewById(R.id.optionList)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnNext = findViewById(R.id.btnNext)
        btnPrevious = findViewById(R.id.btnPrevious)


        setUpFireStore()
        setUpEventListener()
    }

    private fun bindViews() {

        btnPrevious.visibility = View.GONE
        btnSubmit.visibility = View.GONE
        btnNext.visibility = View.GONE

        // for Question 1
        if(index == 1){
            btnNext.visibility = View.VISIBLE
        }

        // for last questtion
        else if(index == questions!!.size){
            btnSubmit.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }

        // for question >1
        else{
            btnNext.visibility = View.VISIBLE
            btnPrevious.visibility = View.VISIBLE
        }


        val question = questions!!["question$index"]
        question?.let {
                description.text = it.description
                val optionAdapter = OptionAdapter(this,it)
                optionList.layoutManager = LinearLayoutManager(this)
                optionList.adapter = optionAdapter
                optionList.setHasFixedSize(true)

        }






    }

    fun setUpFireStore(){
       val firestore = FirebaseFirestore.getInstance()

        var date =intent.getStringExtra("DATE")

        if(date != null){
            firestore.collection("quizzes").whereEqualTo("title",date)
                .get()
                .addOnSuccessListener {
                    if(it != null && !it.isEmpty) {
                        quizzes = it.toObjects(Quiz::class.java)
                        questions = quizzes!![0].questions
                        bindViews()
                    }
                }
        }
    }


    fun setUpEventListener(){
        btnPrevious.setOnClickListener{
            index--
            bindViews()
        }
        btnNext.setOnClickListener{
            index++
            bindViews()
        }
        btnSubmit.setOnClickListener{
            Log.d("FINALQUIZ",questions.toString())

            val intent = Intent(this,ResultActivity::class.java)
            val json = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ",json)
            startActivity(intent)
        }
    }
}