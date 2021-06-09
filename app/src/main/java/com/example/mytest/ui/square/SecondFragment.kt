package com.example.mytest.ui.square

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


class SecondFragment : Fragment() {

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
        val recyclerView: RecyclerView = root.findViewById(R.id.recycleview2)
        val adapter = Info2Adapter(infolist)
        recyclerView.adapter = adapter
        initInfo()
        //获取这个页面的recycleview并且设置瀑布流

        recyclerView.setLayoutManager(
            StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
            )
        )
        //绑定适配器和数组，将适配器作为recycleview的适配器

        recyclerView.adapter = adapter

        /*操作：recyclerview中获得每个item的位置（数组下标）*/
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        // do whatever
                        Log.d("SecondFragment", "you clicked ${infolist[position].name}")
                        //跳转到详情页
//                    val pid:Int = infolist[position].pid
//                    //val intent: Intent = Intent(activity, aDetailActivity::class.java)
//                    val intent: Intent = Intent(activity, initialActivity::class.java)
//                    val bundle = Bundle()
//                    bundle.putInt("pid", pid)
//                    intent.putExtras(bundle)
//                    startActivity(intent)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // do whatever
                    }
                })
        )
        return root
    }

    private fun initInfo() {
        repeat(2) {
            infolist.add(Easy1Msg(1, "卿政庭", "2015年2月10日", R.drawable.qzt, "合川"))
            infolist.add(Easy1Msg(2, "覃钦颢", "2014年12月14日", R.drawable.qqh, "石门县壶瓶山镇后路坪村5组"))
            infolist.add(Easy1Msg(3, "王梦芸", "2016年3月30日", R.drawable.wmy, "云南省曲靖市"))
            infolist.add(Easy1Msg(4, "姜信轩", "1994年", R.drawable.jxx, "不清楚"))
            infolist.add(Easy1Msg(5, "华墨瑶", "2016年11月16日", R.drawable.hmy, "河南"))
            infolist.add(Easy1Msg(6, "党春芸", "2015年9月3日", R.drawable.dcy, "蔚县桃花镇鸭涧村桥北"))


        }

    }
}