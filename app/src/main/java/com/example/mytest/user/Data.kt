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
        //A类型
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
        p1.clues.add(PersonClue(p1.pid,0,"2020年12月1日","我好像上周看到她了"))
        p1.clues.add(PersonClue(p1.pid,1,"2021年1月2日","她现在好像在顺和饭店当服务员？"))

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


        var p11 = Person()
        p11.pid=2
        p11.name = "刘红枚"
        p11.image.add(R.drawable.lhm) //将 yxx 修改为图片名称
        p11.image.add(R.drawable.lhm)
        p11.image.add(R.drawable.lhm)
        p11.location = "湖南省衡阳县"
        p11.date = "1992 年 6 月 5 日"
        p11.age ="47 岁"
        p11.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p11.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p11.height = "1 米 55"
        p11.weight = "50kg"
        p11.appearance = "相貌清秀"
        p11.other ="姐妹寻姐妹"
        p11.s_name = "刘红平"
        p11.s_phone = "18910852236"
        p11.s_address = "未知"
        p11.s_postcode="未知"
        p11.s_relative = "未知"
        p11.clues.add(PersonClue(p11.pid,1,"2020年12月1日","我好像在中心广场看到她了"))


        var p12 = Person()
        p12.pid=3
        p12.name = "张迎娟"
        p12.image.add(R.drawable.zyj) //将 yxx 修改为图片名称
        p12.image.add(R.drawable.zyj)
        p12.image.add(R.drawable.zyj)
        p12.location = "山西运城"
        p12.date = "1995 年 3 月 8 日"
        p12.age ="46 岁"
        p12.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p12.blood = Person.BLOOD_A //BLOOD_A,BLOOD_O,BLOOD_AB
        p12.height = "1 米 58"
        p12.weight = "50kg"
        p12.appearance = "未知"
        p12.other ="张迎娟.女.1975.8.2因家中吵闹离家出走,父亲已于08年8月3日去世.老母急盼女儿的归来"
        p12.s_name = "张文建"
        p12.s_phone = "13203487596"
        p12.s_address = "山西运城市安邑办事处房子村 临猗县合欢花园15号1单元301"
        p12.s_postcode="044000"
        p12.s_relative = "弟弟"

        var p13 = Person()
        p13.pid=4
        p13.name = "宋奕霖"
        p13.image.add(R.drawable.syl) //将 yxx 修改为图片名称
        p13.image.add(R.drawable.syl)
        p13.image.add(R.drawable.syl)
        p13.location = "浙江杭州下沙"
        p13.date = "2015 年 7 月 20 日"
        p13.age ="13 岁"
        p13.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p13.blood = Person.BLOOD_A //BLOOD_A,BLOOD_O,BLOOD_AB
        p13.height = "1 米 30"
        p13.weight = "未知"
        p13.appearance = "嘴唇下有颗痣"
        p13.other ="失散亲友"
        p13.s_name = "宋芝雷"
        p13.s_phone = "18268027140"
        p13.s_address = "浙江杭州下沙"
        p13.s_postcode="未知"
        p13.s_relative = "未知"
        p13.clues.add(PersonClue(p13.pid,1,"2020年12月1日","她好像在我们厂里工作"))

        var p14 = Person()
        p14.pid=5
        p14.name = "胡艳艳"
        p14.image.add(R.drawable.hyy) //将 yxx 修改为图片名称
        p14.image.add(R.drawable.hyy)
        p14.image.add(R.drawable.hyy)
        p14.location = "河南省平顶山市矿工路"
        p14.date = "2018 年 3 月 15 日"
        p14.age ="40 岁"
        p14.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p14.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p14.height = "1 米 55"
        p14.weight = "未知"
        p14.appearance = "肤白，双眼皮，短发"
        p14.other ="因生老公气离家出走打工，出走时电话18837120995，18237587517.亲友打通不接，或接不说具体地址，老公心急"
        p14.s_name = "王域州"
        p14.s_phone = "18838264561"
        p14.s_address = "未知"
        p14.s_postcode="未知"
        p14.s_relative = "丈夫"

        var p15 = Person()
        p15.pid=6
        p15.name = "刘亮"
        p15.image.add(R.drawable.ll) //将 yxx 修改为图片名称
        p15.image.add(R.drawable.ll)
        p15.image.add(R.drawable.ll)
        p15.location = "新疆克拉玛依乌尔禾区"
        p15.date = "1993 年 7 月 13 日"
        p15.age ="43 岁"
        p15.sex = Person.SEX_MALE //SEX_MALE 为男性
        p15.blood = Person.BLOOD_O //BLOOD_A,BLOOD_O,BLOOD_AB
        p15.height = "1 米 67"
        p15.weight = "未知"
        p15.appearance = "平头  圆脸  脸上有雀斑"
        p15.other ="父母寻儿子"
        p15.s_name = "刘先生"
        p15.s_phone = "13139908860"
        p15.s_address = "新疆克拉玛依乌尔禾区137团"
        p15.s_postcode="834014"
        p15.s_relative = "未知"

        var p16 = Person()
        p16.pid=7
        p16.name = "虞崇德"
        p16.image.add(R.drawable.ycd) //将 yxx 修改为图片名称
        p16.image.add(R.drawable.ycd)
        p16.image.add(R.drawable.ycd)
        p16.location = "台湾台南防空站"
        p16.date = "1948 年"
        p16.age ="90 岁"
        p16.sex = Person.SEX_MALE //SEX_MALE 为男性
        p16.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p16.height = "1 米 67"
        p16.weight = "未知"
        p16.appearance = "未知"
        p16.other ="1948年，你年方17岁，随同堂兄阿唐参加了中国国民党军队---温州市东门新兵团，同年随军队转到上海，不久就转到台湾台南防空站。当时你曾经寄回黄屿家里一封信、上江舅舅家二封信、第四封听说被误寄到邻村上田去了。可惜阿三不识字以及当时两岸关系紧张，中国大陆的信根本寄不出去，以致你跟家里失去了联系…"
        p16.s_name = "虞存奇"
        p16.s_phone = "13587850585"
        p16.s_address = "浙江省 温州市 瓯海区 三垟街道 黄屿村 宏发路35号"
        p16.s_postcode="325014"
        p16.s_relative = "哥哥"

        var p17 = Person()
        p17.pid=8
        p17.name = "蒲金翠"
        p17.image.add(R.drawable.pjc) //将 yxx 修改为图片名称
        p17.image.add(R.drawable.pjc)
        p17.image.add(R.drawable.pjc)
        p17.location = "福建厦门市翔安区马巷镇"
        p17.date = "2008 年 9 月 "
        p17.age ="48 岁"
        p17.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p17.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p17.height = "1 米 65"
        p17.weight = "未知"
        p17.appearance = "未知"
        p17.other ="出生于乌鲁木齐，家中兄弟姐妹中最小，有三姐两兄，父母双亡。"
        p17.s_name = "单昇"
        p17.s_phone = "15981756987"
        p17.s_address = "新疆乌鲁木齐市水磨沟区新兴街"
        p17.s_postcode="830000"
        p17.s_relative = "未知"

        var p18 = Person()
        p18.pid=9
        p18.name = "唐孝娥"
        p18.image.add(R.drawable.txe) //将 yxx 修改为图片名称
        p18.image.add(R.drawable.txe)
        p18.image.add(R.drawable.txe)
        p18.location = "长沙"
        p18.date = "1995 年 11 月 2 日"
        p18.age ="48 岁"
        p18.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p18.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p18.height = "1 米 65"
        p18.weight = "未知"
        p18.appearance = "未知"
        p18.other ="湖南道县人，浙大铸造851毕业，当时在长沙1103工厂工作，当时体胖，因为婚姻短暂失败，导致精神严重受挫，但其自身意识很好，可能会在衡山、长沙、西安等地昄依天主教做修女或者入佛门。望知情者提供线索，重酬20万元以上！"
        p18.s_name = "李团万"
        p18.s_phone = "13790442266"
        p18.s_address = "湖南道县万家庄华岩村"
        p18.s_postcode="425300"
        p18.s_relative = "未知"

        var p19 = Person()
        p19.pid=10
        p19.name = "刘晓东"
        p19.image.add(R.drawable.lxd) //将 yxx 修改为图片名称
        p19.image.add(R.drawable.lxd)
        p19.image.add(R.drawable.lxd)
        p19.location = "哈尔滨市中心东大直街南极冷饮厅"
        p19.date = "1997 年 12 月 18 日"
        p19.age ="47 岁"
        p19.sex = Person.SEX_MALE //SEX_MALE 为男性
        p19.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p19.height = "1 米 72"
        p19.weight = "未知"
        p19.appearance = "未知"
        p19.other ="此人出走时，上穿浅米色羽绒大衣，内穿深草绿色棉袄，紫红色绒衣，白色衬衣，下穿藏蓝色裤子，内为黑色棉裤，黑色皮棉鞋。"
        p19.s_name = "刘友方"
        p19.s_phone = "0451-6330690"
        p19.s_address = "哈尔滨市焊接研究所和兴路111号"
        p19.s_postcode="未知"
        p19.s_relative = "未知"





        //B类型
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
        p3.clues.add(PersonClue(p3.pid,1,"2020年12月1日","我好像上周看到她了"))

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

        var p5 = Person()
        p5.pid=2
        p5.name = "李香凝"
        p5.image.add(R.drawable.lxn) //将 yxx 修改为图片名称
        p5.image.add(R.drawable.lxn)
        p5.image.add(R.drawable.lxn)
        p5.location = "河北"
        p5.date = "2017 年 3 月 3 日"
        p5.age ="4 岁"
        p5.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p5.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p5.height = "0.52 米 "
        p5.weight = "30kg"
        p5.appearance = "孩子身上只裹了一个小毯子"
        p5.other ="女儿寻父母"
        p5.s_name = "李新奎"
        p5.s_phone = "13933649105"
        p5.s_address = "河北省秦皇岛市北戴河区"
        p5.s_postcode="066302"
        p5.s_relative = "养母"

        var p6 = Person()
        p6.pid=3
        p6.name = "王帮耀"
        p6.image.add(R.drawable.wby) //将 yxx 修改为图片名称
        p6.image.add(R.drawable.wby)
        p6.image.add(R.drawable.wby)
        p6.location = "安徽省泾县桃花潭镇"
        p6.date = "1982 年 4 月 20 日"
        p6.age ="39 岁"
        p6.sex = Person.SEX_MALE //SEX_MALE 为男性
        p6.blood = Person.BLOOD_A //BLOOD_A,BLOOD_O,BLOOD_AB
        p6.height = "未知"
        p6.weight = "未知"
        p6.appearance = "出生被遗弃在河边 脐带未剪 招风耳"
        p6.other ="帮老公寻父母 希望有生之年见一下父母"
        p6.s_name = "朱丽群"
        p6.s_phone = "13706500393"
        p6.s_postcode="310000"
        p6.s_address = "浙江省杭州市滨江区滨兴小区26栋"
        p6.s_relative = "妻子"


        var p7 = Person()
        p7.pid=4
        p7.name = "周迅康"
        p7.image.add(R.drawable.zxk) //将 yxx 修改为图片名称
        p7.image.add(R.drawable.zxk)
        p7.image.add(R.drawable.zxk)
        p7.location = "贵阳"
        p7.date = "1992 年 6 月 5 日"
        p7.age ="75 岁"
        p7.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p7.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p7.height = "1 米 58"
        p7.weight = "50kg"
        p7.appearance = "未知"
        p7.other ="我这是在网上找我父母 约1992年在贵阳卸煤打工，因苦力受不了另找活干 被骗至河北张家口为家 现求助这平台寻找我父母家人。走时我父母健在 有两个哥哥，现在家人相貌 我已忘记  无法描述"
        p7.s_name = "魏庆斌"
        p7.s_phone = "13331300278"
        p7.s_address = "河北省张家口市沙岭子镇屈家庄"
        p7.s_postcode="075000"
        p7.s_relative = "未知"

        var p8 = Person()
        p8.pid=5
        p8.name = "范梦琪"
        p8.image.add(R.drawable.fmq) //将 yxx 修改为图片名称
        p8.image.add(R.drawable.fmq)
        p8.image.add(R.drawable.fmq)
        p8.location = "河北省张家口市万全"
        p8.date = "2021 年 4 月 2 日"
        p8.age ="75 岁"
        p8.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p8.blood = Person.BLOOD_OTHER //BLOOD_A,BLOOD_O,BLOOD_AB
        p8.height = "0.51 米"
        p8.weight = "50kg"
        p8.appearance = "圆脸，双眼皮，皮肤偏黑"
        p8.other ="收养 女儿寻父母"
        p8.s_name = "范彩东"
        p8.s_phone = "19930330845"
        p8.s_address = "孔家庄镇义兴堡村"
        p8.s_postcode="076250"
        p8.s_relative = "未知"
        p8.clues.add(PersonClue(p8.pid,0,"2020年12月1日","我们族谱上好像有她的名字"))

        var p9 = Person()
        p9.pid=6
        p9.name = "戎智芳"
        p9.image.add(R.drawable.rzf) //将 yxx 修改为图片名称
        p9.image.add(R.drawable.rzf)
        p9.image.add(R.drawable.rzf)
        p9.location = "北京"
        p9.date = "2010 年 3 月 9 日"
        p9.age ="48 岁"
        p9.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p9.blood = Person.BLOOD_A //BLOOD_A,BLOOD_O,BLOOD_AB
        p9.height = "1 米 58 "
        p9.weight = "49kg"
        p9.appearance = "背后有个痣"
        p9.other ="被拐卖"
        p9.s_name = "杨智红"
        p9.s_phone = "03515562634"
        p9.s_address = "陕西，西安，高陵张卜乡南郭九村"
        p9.s_postcode="710200"
        p9.s_relative = "未知"


        var p10 = Person()
        p10.pid=7
        p10.name = "史沛瑶"
        p10.image.add(R.drawable.spy) //将 yxx 修改为图片名称
        p10.image.add(R.drawable.spy)
        p10.image.add(R.drawable.spy)
        p10.location = "南留庄--蔚县县城"
        p10.date = "2019 年 10 月 30 日"
        p10.age ="2 岁"
        p10.sex = Person.SEX_FEMALE //SEX_MALE 为男性
        p10.blood = Person.BLOOD_A //BLOOD_A,BLOOD_O,BLOOD_AB
        p10.height = "0.45 米  "
        p10.weight = "未知"
        p10.appearance = "未知"
        p10.other ="出生后被遗弃"
        p10.s_name = "史作利"
        p10.s_phone = "13315316008"
        p10.s_address = "蔚县南留庄镇涧塄村"
        p10.s_postcode="075700"
        p10.s_relative = "未知"

        //广场初始化
        //A表
        A_List.add(p1)
        A_List.add(p2)
        A_List.add(p11)
        A_List.add(p12)
        A_List.add(p13)
        A_List.add(p14)
        A_List.add(p15)
        A_List.add(p16)
        A_List.add(p17)
        A_List.add(p18)
        A_List.add(p19)

        //B表
        B_List.add(p3)
        B_List.add(p4)
        B_List.add(p5)
        B_List.add(p6)
        B_List.add(p7)
        B_List.add(p8)
        B_List.add(p9)
        B_List.add(p10)

        //发布信息
        my_ListA.add(p1)
        my_ListA.add(p16)

        my_ListB.add(p3)
        my_ListB.add(p9)

        //提供线索
        my_ListClueA.add(p13)
        my_ListClueA.add(p11)
        my_ListClueA.add(p1)

        my_ListClueB.add(p3)
        my_ListClueB.add(p8)

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