package com.vivek.dilyquiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vivek.dilyquiz.R

class LogInintro : AppCompatActivity() {
    private lateinit var btngetstarted:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_inintro)

        val auth:FirebaseAuth = FirebaseAuth.getInstance()
        if(auth.currentUser != null){
            Toast.makeText(this@LogInintro,"User Is already Logged In:",Toast.LENGTH_SHORT).show()
            redirect("Main")
        }


        btngetstarted = findViewById(R.id.btngetstarted)
        btngetstarted.setOnClickListener{

            redirect("LogIN")
        }
    }

    private fun redirect(name:String){
        val intent:Intent = when(name){
            "LogIN" -> Intent(this, LogIn::class.java)
            "Main" -> Intent(this, MainActivity::class.java)
            else -> throw Exception("No Path Exists")
        }
        startActivity(intent)
        finish()
    }
}