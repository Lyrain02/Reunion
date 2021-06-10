package com.example.mytest.ui.square

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.Constraints
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.R
import com.example.mytest.ui.squareDetail.aDetailActivity
import com.example.mytest.ui.squareDetail.bDetailActivity
import com.example.mytest.user.Data
import com.example.mytest.user.Person


class SecondFragment : Fragment() {
    val tag1 ="SecondFragment"
    val infolist: ArrayList<Easy1Msg> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        initInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(Constraints.TAG, "Secondfragment")
        //获取页面的view
        val root: View = inflater.inflate(R.layout.fragment_second, container, false)

        //将人物信息填入数组infolist
        initInfo()

        //获取这个页面的recycleview并且设置瀑布流
        val recyclerView: RecyclerView = root.findViewById(R.id.recycleview2)
        val adapter = Info2Adapter(infolist)
        recyclerView.adapter = adapter
        recyclerView.setLayoutManager(
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        )


        /*操作：recyclerview中获得每个item的位置（数组下标）*/
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        // do whatever
                        Log.d(tag1,"you clicked ${infolist[position].name}")
                        //跳转到详情页
                        val pid: Int = infolist[position].pid
                        val intent: Intent = Intent(activity, bDetailActivity::class.java)
                        val bundle = Bundle()
                        bundle.putInt("pid", pid)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // do whatever
                    }
                })
        )
        return root
    }

    private fun initInfo() {
        val pList = getPersonListB()
        for(p in pList){
            infolist.add(Easy1Msg(p.pid,p.name,p.date,p.image[0],p.location))
        }
    }

    private fun getPersonListB():ArrayList<Person>{
        //连接数据库
        //查询A表信息，返回Person列表
        return Data.B_List
    }
}