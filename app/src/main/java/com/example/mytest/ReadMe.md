# 简介
U代表用户      u_name
P代表走失人员 p_nameA, p_nameB
S代表救助者    s_name
L代表丢失者    l_name



## ui包
各界面的Activity，具体内容见Reunion下的readme文件

------
## user包
1.    User类
存储APP当前登陆的用户信息
2.    Mode类
表示APP使用本地存储，或远程数据库
Mode类中通过Mode.type设置本地/远程存储方式
3.    Person类
存储走失人员的信息（clues仅限本地使用）
4.    PersonClue类
存储人员的线索
5.    Data类（TepUser类）
存储本地数据
------
## utils包
1.    Test.kt
用来测试kotlin代码
2.    Local类
函数实现：本地数据访问
3.    Remote类
函数实现：远程服务器存储数据

------
## 本地数据结构
object Data {
    val userList = ArrayList<TepUser>( )
    
    val A_List = ArrayList<Person>()
    val B_List = ArrayList<Person>()

    val my_ListA = ArrayList<Person>()
    val my_ListB = ArrayList<Person>()
    val my_ListClueA = ArrayList<Person>()
    val my_ListClueB = ArrayList<Person>()
}

