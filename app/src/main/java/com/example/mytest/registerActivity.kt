package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class registerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        supportActionBar?.hide()
        val etusername: EditText =findViewById(R.id.username)
        val etpwd1: EditText =findViewById(R.id.pwd1)
        val etpwd2: EditText =findViewById(R.id.pwd2)
        val button1: Button =findViewById(R.id.btn_register)
        button1.setOnClickListener(){
            val username:String=etusername.text.toString().trim()
            val pwd1:String=etpwd1.text.toString().trim()
            val pwd2:String=etpwd2.text.toString().trim()
            register(username,pwd1,pwd2)
        }
        val btn_back: ImageView =findViewById(R.id.back)
        btn_back.setOnClickListener(){
            val intent = Intent(this,initialActivity::class.java)
            startActivity(intent)
        }

    }

    private fun register(username:String,pwd1:String,pwd2:String) {

        if(username==null||username.length<=0){
            Toast.makeText(this,"用户名不能为空！", Toast.LENGTH_SHORT).show()
            return
        }
        if(pwd1==null||pwd1.length<=0)
        {
            Toast.makeText(this,"密码不能为空！", Toast.LENGTH_SHORT).show()
            return
        }
        if(pwd1!=pwd2) {
            Toast.makeText(this,"两次密码不一致！", Toast.LENGTH_SHORT).show()
            return
        }
        TODO("校验验证码")
    }
}