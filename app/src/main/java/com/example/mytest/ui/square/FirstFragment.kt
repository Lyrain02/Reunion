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
import com.example.mytest.ui.squareDetail.aDetailActivity


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
        initInfo()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d(Constraints.TAG, "Firstfragment" )
        val root:View=  inflater.inflate(R.layout.fragment_first, container, false)
        initView(root)
        initInfo()

        val recyclerView:RecyclerView= root.findViewById(R.id.recycleview1)
        recyclerView.setLayoutManager(StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL))

        val adapter = Info1Adapter(infolist)
        recyclerView.adapter=adapter

        adapter.setOnItemClickListener(object : Info1Adapter.OnItemClickListener{
            override fun OnItemClick(view: View?, data: Easy1Msg?) {
                //此处进行监听事件的业务处理
                Log.d("aaaa","click()")
                Toast.makeText(activity, "我是item", Toast.LENGTH_SHORT).show()
            }
        })

        /*操作：recyclerview中获得每个item的位置（数组下标）*/
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(context, recyclerView, object : RecyclerItemClickListener.OnItemClickListener{
                override fun onItemClick(view: View?, position: Int) {
                    // do whatever
                    Log.d("aaa",position.toString())
                    //跳转到详情页
                    val indx:Int = position
                    val intent: Intent = Intent(activity, aDetailActivity::class.java)
                    val bundle = Bundle()
                    bundle.putInt("id", indx)
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
        repeat(2){
            infolist.add(Easy1Msg("李海鹏","2013年1月12日",R.drawable.lhp,"黑龙江省八五七农场25连"))
            infolist.add(Easy1Msg("刘冬良","2018年4月29日",R.drawable.ldl,"东莞塘厦"))
            infolist.add(Easy1Msg("彭菁","2015年12月10日",R.drawable.pq,"常德市武陵区黄溪堰七组"))
            infolist.add(Easy1Msg("杨群燕","1995年",R.drawable.yqy,"湖南省湘西州凤凰县"))
            infolist.add(Easy1Msg("于晓香","2004年3月7日",R.drawable.yxx,"山东潍坊"))
            infolist.add(Easy1Msg("张军","2001年9月3日",R.drawable.zj,"成都"))
        }
    }

    private fun initView(view: View) {
        Log.d("aaaa","initView1")


    }


}