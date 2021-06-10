package com.example.mytest.ui.myfind

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.MainActivity
import com.example.mytest.R
import com.example.mytest.user.Data
import com.example.mytest.user.Person


class MyFindActivity : AppCompatActivity() {
    private val tag = "MyFindActivity"
    val infoList = ArrayList<myfindMsg>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_find)
        val uid = intent.getIntExtra("uid",0)
        Log.d(tag, "activity_my_find:uid is $uid")

        initInfo(uid)

        //设置瀑布流界面
        val recyclerView:RecyclerView= findViewById(R.id.myfind_recycleview)
        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter =myFindInfoAdapter(infoList)
        recyclerView.adapter=adapter
        Log.d(tag, "recycle_my_find")
    }

    private fun initInfo(uid:Int) {
        val pList = getMyFindList(uid)
        for(p in pList){
            infoList.add(myfindMsg(p.pid,p.name,p.date,p.image[0],p.location,p.status))
        }
    }

    private fun getMyFindList(uid :Int):ArrayList<Person>{
        //连接数据库
        //给定用户uid,获取寻人列表
        //返回值为Person的列表
        return Data.my_ListA //本地数据
    }
}