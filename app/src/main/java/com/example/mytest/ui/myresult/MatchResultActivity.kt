package com.example.mytest.ui.myresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.R
import com.example.mytest.ui.Myclue.MyClueInfoAdapterA
import com.example.mytest.ui.Myclue.MyClueInfoAdapterB
import com.example.mytest.ui.myfind.myFindInfoAdapter
import com.example.mytest.ui.myfind.myfindMsg
import com.example.mytest.ui.square.Easy1Msg
import com.example.mytest.user.Data
import com.example.mytest.user.Person
import org.w3c.dom.Text

class MatchResultActivity : AppCompatActivity() {
    val infolist:ArrayList<myfindMsg> = ArrayList()
    lateinit var viewHolder : ViewHolder

    inner class ViewHolder{
        val pimage = findViewById<ImageView>(R.id.presult_image)
        val pname:TextView = findViewById(R.id.presult_name)
        val pplace :TextView = findViewById(R.id.presult_place)
        val pdate :TextView  = findViewById(R.id.presult_date)
        val pstatus:TextView = findViewById(R.id.presult_status)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_result)
        supportActionBar?.hide()

        viewHolder = ViewHolder()
        val pid  = intent.getIntExtra("pid",0)
        val table  = intent.getStringExtra("table")

        init(pid,table)

        val recyclerView: RecyclerView = findViewById(R.id.myresult_recycleview)
        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        val adapter = when(table){
            "A" -> MyClueInfoAdapterB(infolist) //A表查询结果为B表
            "B" -> MyClueInfoAdapterA(infolist) //B表查询结果为A表
            else -> MyClueInfoAdapterA(infolist) //无意义
        }
        recyclerView.adapter=adapter
    }


    private fun init(pid:Int,table:String?) {
        //初始化上半部分
        val person = when(table){
            "A" -> getPersonInfoA(pid)
            "B"-> getPersonInfoB(pid)
            else-> Person()
        }
        viewHolder.pname.text = person.name
        viewHolder.pimage.setImageResource(person.image[0])
        viewHolder.pplace.text = person.location
        viewHolder.pdate.text = person.pdate
        when(person.status){
            0 -> viewHolder.pstatus.text = "未找到"
            -1 -> viewHolder.pstatus.text= "已撤回"
            1 -> viewHolder.pstatus.text = "已找到"
        }

        //初始化下半部分
        val pList = when(table){
            "A"->getMatchA(pid)
            "B"->getMatchB(pid)
            else->ArrayList<Person>()
        }
        for(p in pList){
            infolist.add(myfindMsg(p.pid,p.name,p.date,p.image[0],p.location,p.status))
        }
    }


    private fun getPersonInfoA(pid:Int):Person{
        //连接数据库
        //[A表]给定pid，获取人员信息，返回类型为Person
        return Data.A_List[pid]
    }

    private fun getPersonInfoB(pid:Int):Person{
        //连接数据库
        //[A表]给定pid，获取人员信息，返回类型为Person
        return Data.B_List[pid]
    }

    private fun getMatchA(pid:Int):ArrayList<Person>{
        //连接数据库
        //给定A表pid，查询B表匹配结果
        //返回ArrayList<Person>
        return Data.B_List
    }

    private fun getMatchB(pid:Int):ArrayList<Person>{
        //连接数据库
        //给定B表pid，查询A表匹配结果
        //返回ArrayList<Person>
        return Data.A_List
    }
}