package com.example.mytest.ui.icon

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.mytest.R


class BackLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.back_layout, this)
        val back = findViewById<ImageView>(R.id.back_icon)
        back.setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
    }
}