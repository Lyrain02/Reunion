package com.example.mytest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.mytest.user.User

class registerActivity : AppCompatActivity() {
    private val tag = "registerActivity"
    private lateinit var viewHolder:ViewHolder
    inner class ViewHolder(){
        val etusername: EditText =findViewById(R.id.username)
        val etpwd1: EditText =findViewById(R.id.pwd1)
        val etpwd2: EditText =findViewById(R.id.pwd2)
        val inputCode : EditText = findViewById(R.id.inputCode)
        val checkCode : TextView = findViewById(R.id.checkCode)
        val button1: Button =findViewById(R.id.btn_register)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val t=Thread({Util.load(Util.store("/Pictures/hello.txt"))})
        t.start()
        t.join()
        supportActionBar?.hide()

        viewHolder = ViewHolder()
        viewHolder.checkCode.text = refreshCheckCode()
        viewHolder.checkCode.setOnClickListener{
            viewHolder.checkCode.text = refreshCheckCode()
            Log.d(tag,"check code is ${viewHolder.checkCode.text}")
        }
        viewHolder.button1.setOnClickListener{

            val inputCheckStr : String =viewHolder.inputCode.text.toString().trim()
            val checkStr : String = viewHolder.checkCode.text.toString().trim()
            val username: String = viewHolder.etusername.text.toString().trim()
            val pwd1: String = viewHolder.etpwd1.text.toString().trim()
            val pwd2: String = viewHolder.etpwd2.text.toString().trim()
            if(isCheckCodeValid(inputCheckStr,checkStr)){
                if (isRegisterValid(username, pwd1, pwd2)) {
                    finish()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun isRegisterValid(username:String,pwd1:String,pwd2:String):Boolean{
        if(username==null||username.length<=2){
            Toast.makeText(this,"用户名长度至少为3位！", Toast.LENGTH_SHORT).show()
            clearUserName(viewHolder.etusername)
            clearPassword(viewHolder.etpwd1,viewHolder.etpwd2)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        if(pwd1==null||pwd1.length<=4)
        {
            Toast.makeText(this,"密码长度至少为5位！", Toast.LENGTH_SHORT).show()
            clearPassword(viewHolder.etpwd1,viewHolder.etpwd2)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        if(pwd1!=pwd2) {
            Toast.makeText(this,"两次密码不一致！", Toast.LENGTH_SHORT).show()
            clearPassword(viewHolder.etpwd1,viewHolder.etpwd2)
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        val id = insertUser(username,pwd1)
        if(id == ""){
            Toast.makeText(this,"注册失败！", Toast.LENGTH_SHORT).show()
            clearCheckCode(viewHolder.inputCode,viewHolder.checkCode)
            return false
        }
        User.login(id,username)
        return true
    }

    private fun insertUser(username: String,pw:String):String{
        var res =""
        val t=Thread(Runnable {
            val signUp = Util.sign_up(username,pw)
            println(signUp)
            if(signUp==404){
                res=""
            }else{
                res= Util.get_user_id(username)["_id"].toString()
            }
        })
        t.start()
        t.join()
        //更新数据库
        //将用户命和密码插入数据库,获取用户id
        //-1表示失败，0为测试用户，1以上为真实用户
        return res
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

    private fun clearPassword(pw1:EditText,pw2:EditText){
        pw1.text.delete(0,pw1.text.length)
        pw2.text.delete(0,pw2.text.length)
    }
}