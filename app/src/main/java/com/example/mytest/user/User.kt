package com.example.mytest.user

import android.util.Log
import com.example.mytest.R
import com.example.mytest.utils.Remote.getUserImageRemote
import com.example.mytest.utils.Remote.getUserStatusRemote

object User{
    private val tag = "User"

    const val AUTH_FINISHED = "已实名"
    const val AUTH_UNFINISHED = "未实名"

    var id:Int = -1      //0为测试用户
    var name:String = ""  //test为测试用户
    var image:Int = R.drawable.eg_init
    var auth:String = AUTH_UNFINISHED


    // 判断用户当前是否可以登陆
    // 若登陆，返回true；否则，返回false
    private fun isValid():Boolean{
        if(id == -1 || name.isEmpty())
            return false
        return true
    }

    fun login(id :Int, name: String){
        if(!isValid()){
            this.id = id
            this.name = name
            this.image = if(Mode.isLocal()){
                Data.userList[id].image
            }else getUserImageRemote(id)
            this.auth = if(Mode.isLocal()){
                Data.userList[id].status
            }else getUserStatusRemote(id)
            Log.d(tag,"Log in Successfully! id is ${User.id}, name is ${User.name}")
        }else{
            Log.d(tag,"Already log in! id is ${User.id},name is ${User.name}")
        }
        log()
    }

    fun logout(){
        this.id = -1
        this.name = ""
        this.image = R.drawable.eg_init
        this.auth = User.AUTH_UNFINISHED
        Log.d(tag,"Log out successfully!")
        log()
    }

    fun log(){
        Log.d(tag,"User info:\n id: ${User.id}, name: " +
                "${User.name}, image: ${User.image}, auth: ${User.auth}")
    }

}