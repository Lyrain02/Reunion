package com.example.mytest.ui.Myclue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.R
import com.example.mytest.ui.myfind.myfindMsg

class MyClueActivity : AppCompatActivity() {
    val infolist: ArrayList<myfindMsg> = ArrayList()
    val infolist2: ArrayList<myfindMsg> = ArrayList()
    override fun onStart() {
        super.onStart()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_clue)
        supportActionBar?.hide()

        //Log.d("aa", "activity_my_find")
        //设置瀑布流界面
        val layoutManager1 = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )
        val layoutManager2 = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )

        initInfo()
        val recyclerView: RecyclerView = findViewById(R.id.myclue_recycleview)
        val recyclerView2: RecyclerView = findViewById(R.id.myclue_recycleview2)

        recyclerView2.layoutManager = layoutManager1
        recyclerView.layoutManager = layoutManager2

        val adapter2 = MyClueInfoAdapter(infolist2)
        val adapter = MyClueInfoAdapter(infolist)
        recyclerView.adapter = adapter
        recyclerView2.adapter=adapter2
        Log.d("aa", "recycle_my_clue")
    }

    private fun initInfo() {
        //加载recycleview中的信息 我的寻人信息
        repeat(2) {
            infolist.add(myfindMsg(1,"李海鹏", "2013年1月12日", R.drawable.lhp, "黑龙江省八五七农场25连", -1))
            infolist.add(myfindMsg(2,"刘冬良", "2018年4月29日", R.drawable.ldl, "东莞塘厦", 0))
            infolist.add(myfindMsg(3,"彭菁", "2015年12月10日", R.drawable.pq, "常德市武陵区黄溪堰七组", 1))
        }
        //加载recycleview2中的信息 我的寻亲信息
        repeat(2){
            infolist2.add(myfindMsg(1,"卿政庭", "2015年2月10日", R.drawable.qzt, "合川",1))
            infolist2.add(myfindMsg(2,"覃钦颢", "2014年12月14日", R.drawable.qqh, "石门县壶瓶山镇后路坪村5组",-1))
            infolist2.add(myfindMsg(3,"王梦芸", "2016年3月30日", R.drawable.wmy, "云南省曲靖市",0))


        }
    }

    fun initview(view: View, info: myfindMsg): View {
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