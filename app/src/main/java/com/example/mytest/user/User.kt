package com.example.mytest.user

import android.util.Log

object User{
    const val tag = "user"
    var id:String = ""      //0为测试用户
    var name:String = "test"

    private fun isValid():Boolean{
        if(id=="" && name =="test")
            return false
        return true
    }

    fun login(id: String, name: String){
        if(!isValid()){
            this.id = id
            this.name = name
            Log.d(tag,"Log in Successfully! id is ${User.id}, name is ${User.name}")
        }else{
            Log.d(tag,"Already log in! id is ${User.id},name is ${User.name}")
        }
    }

    fun logout(){
        this.id = ""
        this.name = "test"
        Log.d(tag,"Log out successfully!")
    }
}