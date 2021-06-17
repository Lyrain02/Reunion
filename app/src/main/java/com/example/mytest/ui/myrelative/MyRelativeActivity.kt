package com.example.mytest.ui.myrelative

import android.content.Intent
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
import com.example.mytest.MainActivity
import com.example.mytest.R
import com.example.mytest.ui.myfind.myFindInfoAdapter
import com.example.mytest.ui.myfind.myfindMsg
import com.example.mytest.ui.square.Easy1Msg
import com.example.mytest.user.Data
import com.example.mytest.user.Mode
import com.example.mytest.user.Person
import com.example.mytest.utils.Local.getMyFindListBLocal
import com.example.mytest.utils.Remote.getMyFindListBRemote

class MyRelativeActivity : AppCompatActivity() {
    private val tag1 = "MyFindActivity"
    val infoList: ArrayList<myfindMsg> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_relative)
        supportActionBar?.hide()
        val uid  = intent.getIntExtra("uid",0)
        Log.d(tag1, "activity_my_find:uid is $uid")

        initInfo(uid)

        //设置瀑布流界面
        val recyclerView: RecyclerView = findViewById(R.id.myrelative_recycleview)
        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        val adapter = MyRelativeAdapter(infoList)
        recyclerView.adapter=adapter
        Log.d(tag1, "recycle_my_relative")

    }

    private fun initInfo(uid:Int) {
        val pList = getMyFindListB(uid)
        for(p in pList){
            infoList.add(myfindMsg(p.pid,p.name,p.date,p.image[0],p.location,p.status))
        }
    }

    private fun getMyFindListB(uid :Int):ArrayList<Person>
    =if(Mode.isLocal()) getMyFindListBLocal(uid)
    else getMyFindListBRemote(uid)

}