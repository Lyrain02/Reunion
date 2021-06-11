package com.example.mytest.utils

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
        //更新数据库
        //将用户命和密码插入数据库,获取用户id
        //-1表示失败，0及以上为真实用户
        return 0
    }

    fun isUserValidRemote(username: String):Boolean{
        //在数据库中查询用户名
        //若存在，返回true；否则，返回false
        return true
    }

    fun isPasswordValidRemote(name:String,pwd:String):Boolean{
        //在数据库中查询用户名和密码是否匹配
        //若匹配，返回true；否则，返回false

        return true
    }

    fun getUserIdRemote(name:String):Int{
        //在数据库中，通过用户名，查询用户id
        //-1表示失败，0为测试用户，1以上为真实用户
        return 0
    }
}