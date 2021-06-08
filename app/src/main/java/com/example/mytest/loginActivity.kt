package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class loginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
        val etusername: EditText =findViewById(R.id.username)
        val etpwd: EditText =findViewById(R.id.pwd)
        val button1: Button =findViewById(R.id.btn_login)

        button1.setOnClickListener(){
            val username:String=etusername.text.toString().trim()
            val pwd1:String=etpwd.text.toString().trim()
            val intent = Intent(this,initialActivity::class.java)
            startActivity(intent)
            login(username,pwd1)
        }
        val btn_back: ImageView =findViewById(R.id.back)
        btn_back.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login(username: String, pwd: String) {
        if(username.length<=0){
            Toast.makeText(this,"用户名不能为空！", Toast.LENGTH_SHORT).show()
            return
        }
        else if(pwd.length<=0)
        {
            Toast.makeText(this,"密码不能为空！", Toast.LENGTH_SHORT).show()
            return
        }




        TODO("校验验证码")
    }
}