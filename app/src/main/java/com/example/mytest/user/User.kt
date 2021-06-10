package com.example.mytest.user

import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.example.mytest.R

object User{
    private val tag = "User"

    const val AUTH_FINISHED = "已实名"
    const val AUTH_UNFINISHED = "未实名"

    var id:Int = -2      //0为测试用户
    var name:String = "no"  //test为测试用户
    var image:Int = R.drawable.eg_girl
    var auth:String = AUTH_UNFINISHED


    private fun isValid():Boolean{
        if(id==-2 && name =="no")
            return false
        return true
    }

    fun login(id :Int, name: String){
        if(!isValid()){
            this.id = id
            this.name = name
            Log.d(tag,"Log in Successfully! id is ${User.id}, name is ${User.name}")
        }else{
            Log.d(tag,"Already log in! id is ${User.id},name is ${User.name}")
        }
        User.log()
    }

    fun logout(){
        this.id = -2
        this.name = "no"
        this.image = R.drawable.eg_girl
        this.auth = User.AUTH_UNFINISHED
        Log.d(tag,"Log out successfully!")
        User.log()
    }

    fun log(){
        Log.d(tag,"User info:\n id: ${User.id}, name: " +
                "${User.name}, image: ${User.image}, auth: ${User.auth}")
    }
}