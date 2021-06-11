package com.example.mytest.ui.square

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.Constraints
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.R
import com.example.mytest.initialActivity
import com.example.mytest.ui.squareDetail.aDetailActivity
import com.example.mytest.ui.squareDetail.bDetailActivity
import com.example.mytest.user.Data
import com.example.mytest.user.Person


class FirstFragment : Fragment() {
    private val tag1 = "FirstFragment"
    val infolist:ArrayList<Easy1Msg> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        //initInfo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d(Constraints.TAG, "Firstfragment" )
        val root:View=  inflater.inflate(R.layout.fragment_first, container, false)

        initInfo()

        val recyclerView:RecyclerView= root.findViewById(R.id.recycleview1)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

        val adapter = Info1Adapter(infolist)
        recyclerView.adapter=adapter

        adapter.setOnItemClickListener(object : Info1Adapter.OnItemClickListener{
            override fun OnItemClick(view: View?, data: Easy1Msg?) {
                //此处进行监听事件的业务处理
                Log.d(tag1,"click()")
                Toast.makeText(activity, "我是item", Toast.LENGTH_SHORT).show()
            }
        })

        /*操作：recyclerview中获得每个item的位置（数组下标）*/
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(context, recyclerView, object : RecyclerItemClickListener.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    // do whatever
                    Log.d(tag1,"you clicked ${infolist[position].name}")
                    //跳转到详情页
                    val pid: Int = infolist[position].pid
                    val intent: Intent = Intent(activity, aDetailActivity::class.java)
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
        val pList = getPersonListA()
        for(p in pList){
            infolist.add(Easy1Msg(p.pid,p.name,p.date,p.image[0],p.location))
        }
    }

    private fun getPersonListA():ArrayList<Person>{
        //连接数据库
        //查询A表信息，返回Person列表
        return Data.A_List
    }

}