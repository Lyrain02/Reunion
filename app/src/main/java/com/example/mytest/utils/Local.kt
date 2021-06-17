package com.example.mytest.utils

import com.example.mytest.R
import com.example.mytest.user.*

//本地数据访问

object Local {

    /*-----------User-----------*/
    fun getUserImageLocal(uid:Int):Int{
        return Data.userList[uid].image
    }

    fun getUserNameLocal(uid:Int):String{
        return Data.userList[uid].name
    }

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

    fun updateUserImageLocal(uid:Int, img:Int):Boolean{
        Data.userList[uid].image = img
        return true
    }

    fun updateUserNameLocal(uid:Int, name: String):Boolean{
        Data.userList[uid].name = name
        return true
    }

    /*-------------PersonList-----------*/
    fun getPersonListALocal():ArrayList<Person>{
        return Data.A_List
    }

    fun getPersonListBLocal():ArrayList<Person>{
        return Data.B_List
    }

    fun getMyFindListALocal(uid :Int):ArrayList<Person>{
        return Data.my_ListA
    }

    fun getMyFindListBLocal(uid :Int):ArrayList<Person>{
        return Data.my_ListB
    }

    fun getMatchALocal(pid:Int):ArrayList<Person>{
        return Data.B_List
    }

    fun getMatchBLocal(pid:Int):ArrayList<Person>{
        return Data.A_List
    }

    /*-----------PersonInfo------------*/
    fun getPersonInfoALocal(pid:Int):Person{
        return Data.A_List[pid]
    }

    fun getPersonInfoBLocal(pid:Int):Person{
        return Data.B_List[pid]
    }

    fun uploadPersonALocal(p:Person):Boolean{
        p.pid = Data.A_List.size    //获取pid
        Data.A_List.add(p)          //本地存储，广场
        Data.my_ListA.add(p)        //本地存储，个人发布
        return true
    }

    fun uploadPersonBLocal(person:Person):Boolean{
        person.pid = Data.B_List.size   //本地存储
        Data.B_List.add(person)         //本地存储，广场
        Data.my_ListB.add(person)       //本地存储，个人发布
        return true
    }

    fun updateStatusALocal(pid:Int,status: Int):Boolean{
        Data.A_List[pid].status = status
        return true
    }

    fun updateStatusBLocal(pid: Int, status: Int): Boolean {
        Data.B_List[pid].status = status
        return true
    }

    /*-------------clue-------------*/
    fun getPersonClueALocal(pid:Int):ArrayList<PersonClue>{
        return Data.A_List[pid].clues
    }

    fun getPersonClueBLocal(pid:Int):ArrayList<PersonClue>{
        return Data.B_List[pid].clues
    }

    fun addClueALocal(pclue:PersonClue):Boolean{
        val person = getPersonInfoALocal(pclue.pid)
        Data.A_List[pclue.pid].clues.add(pclue)
        if(person !in Data.my_ListClueA)
            Data.my_ListClueA.add(person)
        return true
    }

    fun addClueBLocal(pclue: PersonClue):Boolean{
        val person = getPersonInfoBLocal(pclue.pid)
        Data.B_List[pclue.pid].clues.add(pclue)
        if(person !in Data.my_ListClueB)
            Data.my_ListClueB.add(person)
        return true
    }

    fun getMyClueListALocal(uid :Int):ArrayList<Person>{
        return Data.my_ListClueA
    }

    fun getMyClueListBLocal(uid :Int):ArrayList<Person>{
        return Data.my_ListClueB //本地数据
    }

    /*---------------其他--------------*/
    fun getCurrentTimeLocal():String{
        return "2021年6月11日"
    }

    fun uploadImageLocal():Int{
        return R.drawable.eg_girl
    }

    fun changeImageLocal():Int{
        var image = R.drawable.eg_boy
        if(User.image == R.drawable.eg_girl) {
            image = R.drawable.eg_boy
        }else if(User.image==R.drawable.eg_boy){
            image = R.drawable.eg_girl
        }
        return image
    }

    fun authentificationLocal(uid:Int):Boolean{
        Data.userList[uid].status = User.AUTH_FINISHED
        return true
    }

}