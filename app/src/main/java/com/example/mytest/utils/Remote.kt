package com.example.mytest.utils

import com.example.mytest.Api
import com.example.mytest.R
import com.example.mytest.Util
import com.example.mytest.user.Data
import com.example.mytest.user.Person
import com.example.mytest.user.PersonClue
import com.example.mytest.user.User

/*----注释-----*/
//远程服务器数据访问

object Remote {
    /*-----------User-----------*/
    fun getUserImageRemote(uid: Int):Int{
        var res=-1
        val t=Thread {
            println("uid===========================$uid")
            val user = Api.get_user(uid)
            println(user)
            if(user==null){
                res=-1
            }else{
                if(!user.containsKey("image")|| user["image"] ==""){
                    res= R.drawable.eg_girl
                }else{
                    res= Integer.parseInt(user["image"]!!)
                }
            }
        }
        t.start()
        t.join()
        println(R.drawable.eg_girl)
        println("image===========================$res")
        //连接数据库
        //通过用户id，获取用户图像src
        return res
    }

    fun getUserNameRemote(uid: Int):String{
        var res=""
        val t=Thread {
            val user = Api.get_user(uid)
            println(user)
            if(user==null){
                res=""
            }else{
                if(!user.containsKey("name")|| user["name"] ==""){
                    res= Data.userList[1].name
                }else{
                    res= user["name"]!!
                }
            }
        }
        t.start()
        t.join()
        println("username================================$res")
        //连接数据库
        //通过用户id获取用户名
        return res
    }

    fun getUserStatusRemote(uid: Int):String{
        var res=""
        val t=Thread {
            val user = Api.get_user(uid)
            println(user)
            if(user==null){
                res=""
            }else{
                if(!user.containsKey("identify")|| user["identify"] ==""){
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

    fun insertUserRemote(username: String, pw: String):Int{
        var res =""
        val t=Thread(Runnable {
            val signUp = Api.sign_up(username, pw)
            println(signUp)
            if (signUp == 404) {
                res = ""
            } else {
                res = Api.get_user_id(username)["id"].toString()
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

    fun isPasswordValidRemote(name: String, pwd: String):Boolean{
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

    fun getUserIdRemote(name: String):Int{
        var id=""
        val t=Thread(Runnable {
            val signIn = Api.get_user_id(name)
            if (signIn == null) {
                id = ""
            } else {
                id = signIn["id"].toString()
            }
        })
        t.start()
        t.join()
        //在数据库中，通过用户名，查询用户id
        //-1表示失败，0为测试用户，1以上为真实用户
        return if(id=="") -1 else Integer.parseInt(id)
    }

    fun updateUserImageRemote(uid: Int, img: Int):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.change_image(uid, img)
        })
        t.start()
        t.join()
        //连接数据库
        //将用户头像更新为img
        return code==200
    }

    fun updateUserNameRemote(uid: Int, name: String):Boolean{
        var signIn =0
        val t=Thread(Runnable {
            signIn = Api.change_name(uid, name)
        })
        t.start()
        t.join()
        if(signIn!=200){
            return false
        }
        return true
    }


    /*-------------PersonList-----------*/
    fun getPersonListARemote():ArrayList<Person>{
        var people = Data.A_List
        val t=Thread(Runnable {
            people = Api.pool_a()
        })
        t.start()
        t.join()
        return people
    }

    fun getPersonListBRemote():ArrayList<Person>{
        var people = Data.B_List
        val t=Thread(Runnable {
            people = Api.pool_b()
        })
        t.start()
        t.join()
        return people
    }

    fun getMyFindListARemote(uid: Int):ArrayList<Person>{
        var people = Data.my_ListA
        val t=Thread(Runnable {
            people = Api.user_find_my_post_a(uid)
        })
        t.start()
        t.join()
        return people
    }

    fun getMyFindListBRemote(uid: Int):ArrayList<Person>{
        var people = Data.my_ListB
        val t=Thread(Runnable {
            people = Api.user_find_my_post_b(uid)
        })
        t.start()
        t.join()
        return people
    }

    fun getMatchARemote(pid: Int):ArrayList<Person>{
        var people = Data.B_List
        val t=Thread(Runnable {
            people = Api.find_similar_post_b()
        })
        t.start()
        t.join()
        //连接数据库
        //给定A表pid，查询B表匹配结果
        //返回ArrayList<Person>
        return people
    }

    fun getMatchBRemote(pid: Int):ArrayList<Person>{
        var people = Data.A_List
        val t=Thread(Runnable {
            people = Api.find_similar_post_a()
        })
        t.start()
        t.join()
        //连接数据库
        //给定B表pid，查询A表匹配结果
        //返回ArrayList<Person>
        return people
    }

    /*-----------PersonInfo------------*/
    fun getPersonInfoARemote(pid: Int):Person{
        var person = Data.A_List[1]
        val t=Thread(Runnable {
            person = Api.get_detail_a(pid)
        })
        t.start()
        t.join()
        //连接数据库 [A表]
        //[A表]给定pid，获取人员信息，返回类型为Person
        return person
    }

    fun getPersonInfoBRemote(pid: Int):Person{
        var person = Data.B_List[1]
        val t=Thread(Runnable {
            person = Api.get_detail_b(pid)
        })
        t.start()
        t.join()
        //连接数据库 [A表]
        //[A表]给定pid，获取人员信息，返回类型为Person
        return person
    }

    fun uploadPersonARemote(p: Person):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.post_post_a(p)
            println(code)
        })
        t.start()
        t.join()
        //连接数据库[A表]
        //上传Person类的数据，需要生成pid
        return code==200
    }

    fun uploadPersonBRemote(p: Person):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.post_post_b(p)
            println(code)
        })
        t.start()
        t.join()
        //连接数据库[B表]
        //上传Person类的数据，需要生成pid
        return code==200
    }

    fun updateStatusARemote(pid: Int, status: Int):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.update_state_post_a(pid, status.toString())
            println(code)
        })
        t.start()
        t.join()
        //连接数据库 [A表]
        //提供pid，status；将状态修改为status
        return code==200
    }

    fun updateStatusBRemote(pid: Int, status: Int): Boolean {
        var code =-1
        val t=Thread(Runnable {
            code = Api.update_state_post_b(pid, status.toString())
            println(code)
        })
        t.start()
        t.join()
        //连接数据库 [B表]
        //提供pid，status；将状态修改为status
        return code==200
    }

    /*-------------clue-------------*/
    fun getPersonClueARemote(pid: Int):ArrayList<PersonClue>{
        var personClues = Data.A_List[1].clues
        val t=Thread(Runnable {
            personClues = Api.find_clue_a(pid)
        })
        t.start()
        t.join()
        println(personClues)
        //数据库连接 [A表]
        //[A表]给定pid，获取clue的列表，返回值为ArrayList<PersonClue>
        return personClues
    }

    fun getPersonClueBRemote(pid: Int):ArrayList<PersonClue>{
        var personClues = Data.B_List[1].clues
        val t=Thread(Runnable {
            personClues = Api.find_clue_b(pid)
        })
        t.start()
        t.join()
        //数据库连接 [A表]
        //[A表]给定pid，获取clue的列表，返回值为ArrayList<PersonClue>
        return personClues
    }

    fun addClueARemote(pclue: PersonClue):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.clue_a(pclue)
            println(code)
        })
        t.start()
        t.join()
        //连接数据库[A表]
        //将PersonClue上传到数据库
        return code==200
    }

    fun addClueBRemote(pclue: PersonClue):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.clue_b(pclue)
            println(code)
        })
        t.start()
        t.join()
        //连接数据库[B表]
        //将PersonClue上传到数据库
        return code==200
    }

    fun getMyClueListARemote(uid: Int):ArrayList<Person>{
        var people = Data.my_ListClueA
        val t=Thread(Runnable {
            people = Api.user_clue_a(uid)
        })
        t.start()
        t.join()
        //连接数据库
        //给定用户uid,获取评论的Person列表[A表]
        //返回值为Person列表
        return people
    }

    fun getMyClueListBRemote(uid: Int):ArrayList<Person>{
        var people = Data.my_ListClueB
        val t=Thread(Runnable {
            people = Api.user_clue_b(uid)
        })
        t.start()
        t.join()
        //连接数据库
        //给定用户uid,获取评论的Person列表[B表]
        //返回值为Person列表
        return people
    }

    /*---------------其他--------------*/

    fun getCurrentTimeRemote():String{
        //非数据库连接部分，可以暂时不实现
        //获取发布时间
        return "2021年6月11日"
    }

//    fun uploadImageRemote():Int{
//        var setImage = -1
//        val t=Thread(Runnable {
//            val path = "/test.txt"
//            val store = Util.store(path)
//            setImage = Api.set_image(store)
//        })
//        t.start()
//        t.join()
//        // 打开相册，上传照片，并返回照片存储的结果Int
//        return if(setImage==-1) R.drawable.eg_girl else setImage
//    }

    fun authentificationRemote(uid: Int):Boolean{
        var code =-1
        val t=Thread(Runnable {
            code = Api.change_identify(uid)
        })
        t.start()
        t.join()
        // 进行实名认证
        // 并且将认证结果上传数据库
        return code==200
    }

}