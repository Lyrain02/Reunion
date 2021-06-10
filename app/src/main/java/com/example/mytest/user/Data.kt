package com.example.mytest.user

import android.util.ArrayMap
import android.util.ArraySet
import com.example.mytest.R

object Data {
    val tag = "Data"

//    val userList = listOf("test","lyrain","system")
//    val pwList = mapOf("lyrain" to "12345","test" to "12345", "system" to "12345")
    val userList = ArrayList<TepUser>()

    val A_List = ArrayList<Person>()
    val B_List = ArrayList<Person>()

    val my_ListA = ArrayList<Person>()
    val my_ListB = ArrayList<Person>()
    val my_ListClueA = ArrayList<Person>()
    val my_ListClueB = ArrayList<Person>()


    init{
        //User初始化
        var u1 = TepUser()
        u1.id = 0
        u1.name = "test"
        u1.image = R.drawable.eg_girl

        var u2 = TepUser()
        u2.id = 1
        u2.name = "lyrain"
        u2.image =R.drawable.eg_girl

        var u3 = TepUser()
        u3.id = 2
        u3.name ="system"

        Data.userList.add(u1)
        Data.userList.add(u2)
        Data.userList.add(u3)

        //Person+PersonClue初始化
        var p1 = Person()
        p1.pid=0
        p1.name = "于晓香"
        p1.image.add(R.drawable.yxx)
        p1.image.add(R.drawable.yxx)
        p1.image.add(R.drawable.yxx)
        p1.location = "山东省潍坊市"
        p1.date = "2004年3月7日"
        p1.age ="22岁"
        p1.sex = Person.SEX_FEMALE
        p1.blood = Person.BLOOD_B
        p1.height = "1米6"
        p1.weight = "45kg"
        p1.appearance = "左手臂内侧有拇指大小的胎记"
        p1.other ="她唱歌很好听"
        p1.pdate="2018年2月21日"
        p1.s_name = "王芳"
        p1.s_phone = "18827357268"
        p1.s_address = "山东省潍坊市"
        p1.s_relative = "母亲"
        p1.clues.add(PersonClue(p1.pid,0,"2020年12月1日","我好像上周看到他了"))

        var p2 = Person()
        p2.pid=1
        p2.name = "李海鹏"
        p2.image.add(R.drawable.lhp)
        p2.image.add(R.drawable.lhp)
        p2.image.add(R.drawable.lhp)
        p2.location = "黑龙江省八五七农场25连"
        p2.date = "2013年1月12日"
        p2.age ="15岁"
        p2.sex = Person.SEX_MALE
        p2.blood = Person.BLOOD_A
        p2.height = "1米4"
        p2.weight = "50kg"
        p2.appearance = "很可爱"
        p2.other ="他说话有点结巴"
        p2.pdate="2014年5月2日"
        p2.s_name = "李伟"
        p2.s_phone = "18263740192"
        p2.s_address = "黑龙江省八五七农场25连"
        p2.clues.add(PersonClue(p2.pid,0,"2020年6月7日","前几天他在河海小区出现过"))

        var p3 = Person()
        p3.pid=0
        p3.name = "王梦芸"
        p3.image.add(R.drawable.wmy)
        p3.image.add(R.drawable.wmy)
        p3.image.add(R.drawable.wmy)
        p3.location = "云南省曲靖市"
        p3.date = "2016年11月16日"
        p3.age ="10岁"
        p3.sex = Person.SEX_FEMALE
        p3.blood = Person.BLOOD_O
        p3.height = "1米1"
        p3.weight = "30kg"
        p3.appearance = "脸圆圆的，眼睛很大"
        p3.other ="走丢的时候，身上挂着一把长命锁"
        p3.pdate="2018年8月4日"
        p3.s_name = "腾冲收留所"
        p3.s_phone = "12583628376"
        p3.s_address = "云南省腾冲市"
        p3.clues.add(PersonClue(p3.pid,0,"2018年3月1日","好像之前在村里见过这孩子"))

        var p4 = Person()
        p4.pid = 1
        p4.name = "党春芸"
        p4.image.add(R.drawable.dcy)
        p4.image.add(R.drawable.dcy)
        p4.image.add(R.drawable.dcy)
        p4.location = "蔚县桃花镇鸭涧村桥北"
        p4.date = "2015年9月3日"
        p4.age ="16岁"
        p4.sex = Person.SEX_MALE
        p4.blood = Person.BLOOD_A
        p4.height = "1米6"
        p4.weight = "60kg"
        p4.appearance = "长得比较黑"
        p4.other ="说话口音很重"
        p4.pdate="2016年1月9日"
        p4.s_name = "阳光救助站"
        p4.s_phone = "010-4859320"
        p4.s_address = "河北省张家口市"
        p4.clues.add(PersonClue(p4.pid,0,"2015年12月2日","好像是我叔的同事的儿子"))


        repeat(4){
            A_List.add(p1)
            A_List.add(p2)
            B_List.add(p3)
            B_List.add(p4)
        }

        my_ListA.add(p1)
        my_ListA.add(p2)
        my_ListB.add(p3)
        my_ListB.add(p4)

        my_ListClueA.add(p1)
        my_ListClueA.add(p2)
        my_ListClueB.add(p3)
        my_ListClueB.add(p4)

    }
}

//本地使用
class TepUser{
    var id = -1
    lateinit var name :String
    var image = R.drawable.eg_boy
    var status = User.AUTH_UNFINISHED
    var pw ="12345"
}