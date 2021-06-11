package com.example.mytest.user

class Person{
    var pid = 0
    val image = ArrayList<Int>()
    var name:String = "暂缺"
    var location:String = "暂缺"
    var date: String = "暂缺"
    var age :String="暂缺"
    var birth:String="暂缺"
    var sex:String="暂缺"
    var blood:String="暂缺"
    var height:String="暂缺"
    var weight:String="暂缺"
    var appearance:String="暂缺"
    var other:String="暂缺"
    var status = Person.STATUS_LOST
    var pdate:String = "2020年1月1日"

    var s_name = "暂缺"
    var s_phone = "暂缺"
    var s_address = "暂缺"
    var s_postcode = "暂缺"
    var s_relative = "暂缺"

    //clues仅限本地使用，连接数据库时不需要该项
    val clues = ArrayList<PersonClue>()

    companion object{
        const val STATUS_LOST = 0
        const val STATUS_FIND = 1
        const val STATUS_CANCEL = -1

        const val SEX_MALE = "男性"
        const val SEX_FEMALE = "女性"
        const val SEX_OTHER = "其他"

        const val BLOOD_A = "A型"
        const val BLOOD_B = "B型"
        const val BLOOD_O = "O型"
        const val BLOOD_AB = "AB型"
        const val BLOOD_OTHER ="其他"
    }
}