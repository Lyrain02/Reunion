package com.example.mytest.utils

import com.example.mytest.Api
import com.example.mytest.R
import com.example.mytest.user.Data
import com.example.mytest.user.Person
import com.example.mytest.user.PersonClue
import com.example.mytest.user.User

/*----注释-----*/
//远程服务器数据访问

object Remote {
    /*-----------User-----------*/
    fun getUserImageRemote(uid:Int):Int{
        //连接数据库
        //通过用户id，获取用户图像src
        return Data.userList[uid].image
    }

    fun getUserNameRemote(uid:Int):String{
        //连接数据库
        //通过用户id获取用户名
        return Data.userList[uid].name
    }

    fun getUserStatusRemote(uid:Int):String{
        var res=""
        val t=Thread {
            val user = Api.get_user(uid)
            println(user)
            if(user==null){
                res=""
            }else{
                if(!user.containsKey("identify")||user.get("identify")==""){
                    res= User.AUTH_UNFINISHED
                }else{
                    res= User.AUTH_FINISHED
                }
            }
        }
        t.start()
        t.join()
        //连接数据库
        //通过用户id，获取用户状态
        return res
    }

    fun insertUserRemote(username: String,pw:String):Int{
        var res =""
        val t=Thread(Runnable {
            val signUp = Api.sign_up(username, pw)
            println(signUp)
            if(signUp==404){
                res=""
            }else{
                res= Api.get_user_id(username)["id"].toString()
            }
        })
        t.start()
        t.join()
        println(Integer.parseInt(res))
        //更新数据库
        //将用户命和密码插入数据库,获取用户id
        //-1表示失败，0及以上为真实用户
        return if(res=="") -1 else Integer.parseInt(res)
    }

    fun isUserValidRemote(username: String):Boolean{
        var signIn =0
        val t=Thread(Runnable {
            signIn = Api.sign_in(username, "xxxxxx")
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
            signIn = Api.sign_in(name, pwd)
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
            val signIn = Api.get_user_id(name)
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

    fun updateUserImageRemote(uid:Int, img:Int):Boolean{
        //连接数据库
        //将用户头像更新为img
        return true
    }

    fun updateUserNameRemote(uid:Int, name: String):Boolean{
        //向服务器上传用户名
        return true
    }


    /*-------------PersonList-----------*/
    fun getPersonListARemote():ArrayList<Person>{
        //连接数据库
        //查询A表信息，返回Person列表
        return Data.A_List
    }

    fun getPersonListBRemote():ArrayList<Person>{
        //连接数据库
        //查询A表信息，返回Person列表
        return Data.B_List
    }

    fun getMyFindListARemote(uid :Int):ArrayList<Person>{
        //连接数据库
        //给定用户uid,获取寻人列表[A表]
        //返回值为Person的列表
        return Data.my_ListA //本地数据
    }

    fun getMyFindListBRemote(uid :Int):ArrayList<Person>{
        //连接数据库
        //给定用户uid,获取救助列表[B表]
        //返回值为Person的列表
        return Data.my_ListB //本地数据
    }

    fun getMatchARemote(pid:Int):ArrayList<Person>{
        //连接数据库
        //给定A表pid，查询B表匹配结果
        //返回ArrayList<Person>
        return Data.B_List
    }

    fun getMatchBRemote(pid:Int):ArrayList<Person>{
        //连接数据库
        //给定B表pid，查询A表匹配结果
        //返回ArrayList<Person>
        return Data.A_List
    }

    /*-----------PersonInfo------------*/
    fun getPersonInfoARemote(pid:Int):Person{
        //连接数据库 [A表]
        //[A表]给定pid，获取人员信息，返回类型为Person
        return Data.A_List[pid]
    }

    fun getPersonInfoBRemote(pid:Int):Person{
        //连接数据库 [A表]
        //[A表]给定pid，获取人员信息，返回类型为Person
        return Data.B_List[pid]
    }

    fun uploadPersonARemote(p:Person):Boolean{
        //连接数据库[A表]
        //上传Person类的数据，需要生成pid
        return true
    }

    fun uploadPersonBRemote(p:Person):Boolean{
        //连接数据库[B表]
        //上传Person类的数据，需要生成pid
        return true
    }

    fun updateStatusARemote(pid:Int,status: Int):Boolean{
        //连接数据库 [A表]
        //提供pid，status；将状态修改为status
        return true
    }

    fun updateStatusBRemote(pid: Int, status: Int): Boolean {
        //连接数据库 [B表]
        //提供pid，status；将状态修改为status
        return true
    }

    /*-------------clue-------------*/
    fun getPersonClueARemote(pid:Int):ArrayList<PersonClue>{
        //数据库连接 [A表]
        //[A表]给定pid，获取clue的列表，返回值为ArrayList<PersonClue>
        return Data.A_List[pid].clues
    }

    fun getPersonClueBRemote(pid:Int):ArrayList<PersonClue>{
        //数据库连接 [A表]
        //[A表]给定pid，获取clue的列表，返回值为ArrayList<PersonClue>
        return Data.B_List[pid].clues
    }

    fun addClueARemote(pclue:PersonClue):Boolean{
        //连接数据库[A表]
        //将PersonClue上传到数据库
        return true
    }

    fun addClueBRemote(pclue: PersonClue):Boolean{
        //连接数据库[A表]
        //将PersonClue上传到数据库
        return true
    }

    fun getMyClueListARemote(uid :Int):ArrayList<Person>{
        //连接数据库
        //给定用户uid,获取评论的Person列表[A表]
        //返回值为Person列表
        return Data.my_ListClueA
    }

    fun getMyClueListBRemote(uid :Int):ArrayList<Person>{
        //连接数据库
        //给定用户uid,获取评论的Person列表[B表]
        //返回值为Person列表
        return Data.my_ListClueB //本地数据
    }

    /*---------------其他--------------*/

    fun getCurrentTimeRemote():String{
        //非数据库连接部分，可以暂时不实现
        //获取发布时间
        return "2021年6月11日"
    }

    fun uploadImageRemote():Int{
        // 打开相册，上传照片，并返回照片存储的结果Int
        return R.drawable.eg_girl
    }

    fun authentificationRemote(uid:Int):Boolean{
        // 进行实名认证
        // 并且将认证结果上传数据库
        return true
    }

}