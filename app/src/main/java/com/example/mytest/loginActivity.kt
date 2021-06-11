package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.mytest.user.Mode
import com.example.mytest.user.User
import com.example.mytest.utils.Local.getUserIdLocal
import com.example.mytest.utils.Local.isPasswordValidLocal
import com.example.mytest.utils.Local.isUserValidLocal
import com.example.mytest.utils.Remote.getUserIdRemote
import com.example.mytest.utils.Remote.isPasswordValidRemote
import com.example.mytest.utils.Remote.isUserValidRemote

class loginActivity : AppCompatActivity() {
    private val tag = "loginActivity"
    private lateinit var viewHolder:ViewHolder

    inner class ViewHolder(){
        val etusername: EditText =findViewById(R.id.username2)
        val etpwd: EditText =findViewById(R.id.pwd)
        val inputCode : EditText = findViewById(R.id.inputCode2)
        val checkCode : TextView = findViewById(R.id.checkCode2)
        val button1: Button =findViewById(R.id.btn_login)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        viewHolder = ViewHolder()
        viewHolder.checkCode.text = refreshCheckCode()
        viewHolder.checkCode.setOnClickListener{
            viewHolder.checkCode.text = refreshCheckCode()
            Log.d(tag,"check code is ${viewHolder.checkCode.text}")
        }

        viewHolder.button1.setOnClickListener(){
            val inputCheckStr : String =viewHolder.inputCode.text.toString().trim()
            val checkStr : String = viewHolder.checkCode.text.toString().trim()
            val username:String=viewHolder.etusername.text.toString().trim()
            val pwd:String=viewHolder.etpwd.text.toString().trim()

            if(isCheckCodeValid(inputCheckStr,checkStr)){
                if (isLoginValid(username, pwd)) {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }


    private fun isLoginValid(username:String,pwd:String):Boolean{
        if(username.length<=2){
            Toast.makeText(this,"用户名长度至少为3位！", Toast.LENGTH_SHORT).show()
            clearUserName(viewHolder.etusername)
            clearPassword(viewHolder.etpwd)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        if(pwd.length<=4)
        {
            Toast.makeText(this,"密码长度至少为5位！", Toast.LENGTH_SHORT).show()
            clearPassword(viewHolder.etpwd)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        if(!isUserValid(username)){
            Toast.makeText(this,"用户名不存在",Toast.LENGTH_LONG).show()
            clearUserName(viewHolder.etusername)
            clearPassword(viewHolder.etpwd)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        if(!isPasswordValid(username,pwd)){
            Toast.makeText(this,"密码错误",Toast.LENGTH_LONG).show()
            clearPassword(viewHolder.etpwd)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        val id = getUserId(username)
        User.login(id,username)
        return true
    }

    private fun isUserValid(username:String):Boolean
    = if(Mode.isLocal()) isUserValidLocal(username)
        else isUserValidRemote(username)

    private fun isPasswordValid(username: String,pwd: String):Boolean
    = if(Mode.isLocal()) isPasswordValidLocal(username,pwd)
        else isPasswordValidRemote(username,pwd)

    private fun getUserId(username:String):Int
    = if(Mode.isLocal()) getUserIdLocal(username)
    else getUserIdRemote(username)

    private fun refreshCheckCode():String{
        val strBuffer = StringBuffer()
        for(i in 0 until 4){
            val num = (0..9).random()
            strBuffer.append(num)
        }
        return strBuffer.toString()
    }

    private fun isCheckCodeValid(input:String,check:String):Boolean{
        if(input!=check){
            Toast.makeText(this,"验证码错误！",Toast.LENGTH_LONG).show()
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        return true
    }

    private fun clearCheckCode(inputCode:EditText,checkCode:TextView){
        checkCode.text = refreshCheckCode()
        inputCode.text.delete(0,inputCode.text.length)
    }

    private fun clearUserName(username:EditText){
        username.text.delete(0,username.text.length)
    }

    private fun clearPassword(pw1:EditText){
        pw1.text.delete(0,pw1.text.length)
    }
}