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

class MyRelativeActivity : AppCompatActivity() {
    val infolist: ArrayList<myfindMsg> = ArrayList()
    override fun onStart() {
        super.onStart()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_relative)
        supportActionBar?.hide()

        //返回我的信息界面按钮
        val img_back: ImageView =findViewById(R.id.back_myfind)
        img_back.setOnClickListener(){

            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", 3)
            startActivity(intent)
        }

        Log.d("aa", "activity_my_find")
        //设置瀑布流界面
        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        val recyclerView: RecyclerView = findViewById(R.id.myrelative_recycleview)
        recyclerView.layoutManager = layoutManager
        initInfo()
        val adapter = myFindInfoAdapter(infolist)
        recyclerView.adapter=adapter
        Log.d("aa", "recycle_my_relative")

    }

    private fun initInfo() {
        //加载recycleview中的信息
        repeat(2){
            infolist.add(myfindMsg("卿政庭", "2015年2月10日", R.drawable.qzt, "合川",1))
            infolist.add(myfindMsg("覃钦颢", "2014年12月14日", R.drawable.qqh, "石门县壶瓶山镇后路坪村5组",-1))
            infolist.add(myfindMsg("王梦芸", "2016年3月30日", R.drawable.wmy, "云南省曲靖市",0))
            infolist.add(myfindMsg("姜信轩", "1994年", R.drawable.jxx, "不清楚",1))
            infolist.add(myfindMsg("华墨瑶", "2016年11月16日", R.drawable.hmy, "河南",-1))

        }
    }



    private fun initview(view: View, info: myfindMsg): View {
        Log.d("aaaa", "initView_myfind")
        view.findViewById<TextView>(R.id.pfind_place).text = info.place
        view.findViewById<TextView>(R.id.pfind_name).text = info.name
        view.findViewById<TextView>(R.id.pfind_date).text = info.datetime
        view.findViewById<ImageView>(R.id.pfind_image).setImageResource(info.imageId)

        val kk: Int = info.status
        when (kk) {
            0 -> view.findViewById<TextView>(R.id.myfind_status).text = "未找到"
            -1 -> view.findViewById<TextView>(R.id.myfind_status).text = "已撤回"
            1 -> view.findViewById<TextView>(R.id.myfind_status).text = "已找到"
        }

        return view

    }

}