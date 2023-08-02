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

class LogIn : AppCompatActivity() {

    lateinit var emailL: EditText
    lateinit var passL: EditText
    lateinit var btnwlcm:Button
    lateinit var btnsignup:TextView

    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        firebaseAuth = FirebaseAuth.getInstance()

        btnwlcm = findViewById(R.id.btnWlcmBack)
        btnsignup = findViewById(R.id.btnsignup)

        btnwlcm.setOnClickListener{
            LogIn()

        }
        btnsignup.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
            finish()
        }
    }

    @SuppressLint("NotConstructor")
    private fun LogIn(){
        emailL = findViewById(R.id.etloginEmail)
        passL = findViewById(R.id.etLoginPass)

        val EmailL = emailL.text.toString()
        val PassL = passL.text.toString()

        if(EmailL.isBlank() || PassL.isBlank()){
            Toast.makeText(this,"Email Or Password Can't Be Empty",Toast.LENGTH_SHORT).show()
            return

        }


        firebaseAuth.signInWithEmailAndPassword(EmailL,PassL).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this,"LogIn SuccessFul",Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this,"Wrong Credentials? ",Toast.LENGTH_SHORT).show()
            }
        }


    }
}