package com.vivek.dilyquiz.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.vivek.dilyquiz.R

class SignUp : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var pass:EditText
    lateinit var cnfpass:EditText
    lateinit var btnSignUp: Button
    lateinit var btngotologinPage:TextView

    lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()


        btnSignUp = findViewById(R.id.btngoon)
        btngotologinPage = findViewById(R.id.btngotologinPage)

        btnSignUp.setOnClickListener{
            SignupUser()
        }

        btngotologinPage.setOnClickListener{
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun SignupUser(){
        email = findViewById(R.id.etEmailAddress)
        val stremail = email.text.toString()
        pass = findViewById(R.id.etSignpassAdd)
        val strpass = pass.text.toString()
        cnfpass = findViewById(R.id.etcnfpass)
       val strcnfpass = cnfpass.text.toString()

   //  FireBase Authentication

        if(stremail.isBlank() || strpass.isBlank() || strcnfpass.isBlank()){
            Toast.makeText(this@SignUp,"Email Or Password Can't Be Blank",Toast.LENGTH_SHORT).show()
            return

        }

        if(strpass != strcnfpass){
            Toast.makeText(this@SignUp,"Password And ConfirmPassword Should Be Same",Toast.LENGTH_SHORT).show()
            return
        }


        firebaseAuth.createUserWithEmailAndPassword(stremail,strpass)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this@SignUp,"LogIn Successful",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this@SignUp,"Error provided by User",Toast.LENGTH_SHORT).show()
                }
            }

    }

}