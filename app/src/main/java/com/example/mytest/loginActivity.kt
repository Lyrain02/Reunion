package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.mytest.user.User

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

    private fun isUserValid(name: String):Boolean{
        //在数据库中查询用户名
        //若存在，返回true；否则，返回false
        return true
    }

    private fun isPasswordValid(name:String,pwd:String):Boolean{
        //在数据库中查询用户名和密码是否匹配
        //若匹配，返回true；否则，返回false
        return true
    }

    private fun getUserId(name:String):Int{
        //在数据库中，通过用户名，查询用户id
        //-1表示失败，0为测试用户，1以上为真实用户
        return 0
    }

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