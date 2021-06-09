package com.example.mytest

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class initialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView: View = window.decorView
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.statusBarColor = Color.TRANSPARENT
        }
        setContentView(R.layout.activity_initial)
        supportActionBar?.hide()

        val button1: Button =findViewById(R.id.btn_login)
        val button2: Button =findViewById(R.id.btn_register)
        button1.setOnClickListener{
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener{
            val intent = Intent(this,registerActivity::class.java)
            startActivity(intent)
        }

    }
}