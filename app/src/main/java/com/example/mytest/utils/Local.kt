package com.example.mytest.utils

import com.example.mytest.user.Data
import com.example.mytest.user.TepUser

//本地数据访问

object Local {

    fun insertUserLocal(username:String, pwd:String):Int{
        for(u in Data.userList){
            if (u.name == username)
                return -1
        }
        val uid = Data.userList.size
        Data.userList.add(TepUser(uid,username,pwd))
        return uid
    }

    fun isUserValidLocal(username: String):Boolean{
        for(u in Data.userList){
            if(u.name == username)
                return true
        }
        return false
    }

    fun isPasswordValidLocal(username: String,pwd: String):Boolean{
        for(u in Data.userList){
            if(u.name == username && u.pw == pwd)
                return true
        }
        return false
    }

    fun getUserIdLocal(username: String):Int{
        for(u in Data.userList){
            if(u.name == username)
                return u.id
        }
        return -1
    }
}