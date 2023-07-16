package com.vivek.calculator

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SpalshScreen : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)

      val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
            // always use finish otherwise after pressing back button
            // it will move to the splash screen
            finish()
        },1500)
    }
}