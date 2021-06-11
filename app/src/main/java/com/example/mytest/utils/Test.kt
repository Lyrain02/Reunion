package com.example.mytest.utils

import com.example.mytest.user.Data
import com.example.mytest.user.Mode

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
    Mode.getModeType()
    print(Mode.isLocal())
}