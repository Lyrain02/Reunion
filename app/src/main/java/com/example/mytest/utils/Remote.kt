package com.example.mytest.utils

import com.example.mytest.Api.*
import com.example.mytest.user.Data

/*----注释-----*/
//远程服务器数据访问

object Remote {
    fun getUserImageRemote(uid:Int):Int{
        //连接数据库
        //通过用户id，获取用户图像src
        return Data.userList[uid].image
    }

    fun getUserStatusRemote(uid:Int):String{
        //连接数据库
        //通过用户id，获取用户状态
        return Data.userList[uid].status
    }

    fun insertUserRemote(username: String,pw:String):Int{

        var res =""
        val t=Thread(Runnable {
            val signUp = sign_up(username,pw)
            println(signUp)
            if(signUp==404){
                res=""
            }else{
                res= get_user_id(username)["id"].toString()
            }
        })
        t.start()
        t.join()
        println(Integer.parseInt(res))
        //更新数据库
        //将用户命和密码插入数据库,获取用户id
        //-1表示失败，0及以上为真实用户
        return return if(res=="") -1 else Integer.parseInt(res)
    }

    fun isUserValidRemote(username: String):Boolean{
        var signIn =0
        val t=Thread(Runnable {
            signIn = sign_in(username,"xxxxxx")
        })
        t.start()
        t.join()
        if(signIn==404){
            return false
        }
        //在数据库中查询用户名
        //若存在，返回true；否则，返回false
        return true
    }

    fun isPasswordValidRemote(name:String,pwd:String):Boolean{
        var signIn =0
        val t=Thread(Runnable {
            signIn = sign_in(name,pwd)
        })
        t.start()
        t.join()
        if(signIn!=200){
            return false
        }
        //在数据库中查询用户名和密码是否匹配
        //若匹配，返回true；否则，返回false
        return true
    }

    fun getUserIdRemote(name:String):Int{
        var id=""
        val t=Thread(Runnable {
            val signIn = get_user_id(name)
            if (signIn == null){
                id=""
            }else{
                id= signIn["id"].toString()
            }
        })
        t.start()
        t.join()
        //在数据库中，通过用户名，查询用户id
        //-1表示失败，0为测试用户，1以上为真实用户
        return if(id=="") -1 else Integer.parseInt(id)
    }
}