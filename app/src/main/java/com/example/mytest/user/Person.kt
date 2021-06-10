package com.example.mytest.user

class Person{
    var pid = 0
    val image = ArrayList<Int>()
    var name:String = ""
    var location:String = ""
    var date: String = ""
    var age :String=""
    var birth:String=""
    var sex:String=""
    var blood:String=""
    var height:String=""
    var weight:String=""
    var appearance:String=""
    var other:String=""
    var status = Person.STATUS_LOST
    var pdate:String = ""

    var s_name = ""
    var s_phone = ""
    var s_address = ""
    var s_postcode = ""
    var s_relative = ""

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