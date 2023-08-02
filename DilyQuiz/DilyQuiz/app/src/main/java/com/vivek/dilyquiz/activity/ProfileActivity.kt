package com.vivek.dilyquiz.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.vivek.dilyquiz.R

class ProfileActivity : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    lateinit var txtEmail:TextView
    lateinit var btnLogOut:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        firebaseAuth = FirebaseAuth.getInstance()
        txtEmail = findViewById(R.id.txtEmail)
        txtEmail.text = firebaseAuth.currentUser?.email

        btnLogOut = findViewById(R.id.btnLogout)

        btnLogOut.setOnClickListener{

            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this,LogIn::class.java)
            startActivity(intent)

        }



    }
}