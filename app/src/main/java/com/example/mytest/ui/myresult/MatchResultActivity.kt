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
import com.example.mytest.ui.myfind.myFindInfoAdapter
import com.example.mytest.ui.myfind.myfindMsg
import com.example.mytest.ui.square.Easy1Msg
import org.w3c.dom.Text

class MatchResultActivity : AppCompatActivity() {
    val infolist:ArrayList<myfindMsg> = ArrayList()
    override fun onStart() {
        super.onStart()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_result)
        supportActionBar?.hide()
        
        val pname:TextView = findViewById(R.id.presult_name)
        val pplace :TextView = findViewById(R.id.presult_place)
        val pdate :TextView  = findViewById(R.id.presult_date)
        val pstatus:TextView = findViewById(R.id.myresult_status)



        val layoutManager = StaggeredGridLayoutManager(1,
            StaggeredGridLayoutManager.VERTICAL)
        val recyclerView: RecyclerView = findViewById(R.id.myresult_recycleview)
        recyclerView.layoutManager = layoutManager
        initInfo()
        val adapter = myFindInfoAdapter(infolist)
        recyclerView.adapter=adapter
        Log.d("aa", "recycle_my_result")

    }


    private fun initInfo() {
        //加载recycleview中的信息
        repeat(2){
            infolist.add(myfindMsg(1,"李海鹏","2013年1月12日",R.drawable.lhp,"黑龙江省八五七农场25连",-1))
            infolist.add(myfindMsg(2,"刘冬良","2018年4月29日",R.drawable.ldl,"东莞塘厦",0))
            infolist.add(myfindMsg(3,"彭菁","2015年12月10日",R.drawable.pq,"常德市武陵区黄溪堰七组",1))
            infolist.add(myfindMsg(4,"杨群燕","1995年",R.drawable.yqy,"湖南省湘西州凤凰县",0))
            infolist.add(myfindMsg(5,"于晓香","2004年3月7日",R.drawable.yxx,"山东潍坊",-1))
            infolist.add(myfindMsg(6,"张军","2001年9月3日",R.drawable.zj,"成都",0))


        }
    }

    private fun setView(myfindlinearlayout: LinearLayout) {
        if (myfindlinearlayout.getParent() != null) {
            (myfindlinearlayout.getParent() as ViewGroup).removeView(myfindlinearlayout)
        }
        myfindlinearlayout.removeAllViews()
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.topMargin = 10

        val view: View = View.inflate(this, R.layout.find_info, null)
        if (myfindlinearlayout.getParent() != null) {
            (myfindlinearlayout.getParent() as ViewGroup).removeView(myfindlinearlayout)
        }
        myfindlinearlayout.removeAllViews()
        repeat(2) {
            initview(view, myfindMsg(1,"李海鹏", "2013年1月12日", R.drawable.lhp, "黑龙江省八五七农场25连", 0))
            myfindlinearlayout.addView(view)
            initview(view, myfindMsg(2,"刘冬良", "2018年4月29日", R.drawable.ldl, "东莞塘厦", 1))
            myfindlinearlayout.addView(view)
            initview(view, myfindMsg(3,"彭菁", "2015年12月10日", R.drawable.pq, "常德市武陵区黄溪堰七组", -1))
            myfindlinearlayout.addView(view)
            initview(view, myfindMsg(4,"杨群燕", "1995年", R.drawable.yqy, "湖南省湘西州凤凰县", 0))
            myfindlinearlayout.addView(view)
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