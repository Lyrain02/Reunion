package com.example.mytest.utils

import com.example.mytest.user.Data

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
//    Data.init()
    val p = Data.A_List
    for(i in p){
        println("id is ${i.pid}, name is ${i.name}")
    }
}