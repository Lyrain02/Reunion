package com.example.mytest.ui.squareDetail

import android.net.rtp.RtpStream
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mytest.R


class aDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_detail)
        supportActionBar?.hide()
        val textview:TextView = findViewById(R.id.id_afind)

        val intent = intent
        val bundle = intent.extras
        val RTableCategoryID = bundle!!.getInt("id")
        Log.d("aaaa","abx "+ RTableCategoryID.toString())
        textview.text = RTableCategoryID.toString()

    }
}