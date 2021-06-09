package com.example.mytest.utils

import com.example.mytest.user.User

class Test {

}

private fun refreshCheckCode():String{
    val strBuffer = StringBuffer()
    for(i in 0 until 4){
        val num = (0..9).random()
        strBuffer.append(num)
    }
    return strBuffer.toString()
}


private fun main(){
//    println(refreshCheckCode())
//    println("User's id is ${User.id}, name is ${User.name}")
//    User.login(5,"Jack")
//    println("User's id is ${User.id}, name is ${User.name}")
//    User.logout()
//    println("User's id is ${User.id}, name is ${User.name}")
//    User.login(2,"Sam")
//    println("User's id is ${User.id}, name is ${User.name}")
}