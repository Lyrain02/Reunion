package com.example.mytest.ui.Myclue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.R
import com.example.mytest.ui.myfind.myfindMsg
import com.example.mytest.user.Data
import com.example.mytest.user.Mode
import com.example.mytest.user.Person
import com.example.mytest.utils.Local.getMyClueListALocal
import com.example.mytest.utils.Local.getMyClueListBLocal
import com.example.mytest.utils.Remote.getMyClueListARemote
import com.example.mytest.utils.Remote.getMyClueListBRemote

class MyClueActivity : AppCompatActivity() {
    private val tag ="MyClueActivity"
    val infolistA: ArrayList<myfindMsg> = ArrayList()
    val infolistB: ArrayList<myfindMsg> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_clue)
        supportActionBar?.hide()
        val uid = intent.getIntExtra("uid",0)
        Log.d(tag, "activity_my_find: uid is $uid")

        initInfo(uid)


        //设置瀑布流界面
        val layoutManager1 = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )
        val layoutManager2 = StaggeredGridLayoutManager(
            1,
            StaggeredGridLayoutManager.VERTICAL
        )

        val recyclerViewA: RecyclerView = findViewById(R.id.myclue_recycleview)
        val recyclerViewB: RecyclerView = findViewById(R.id.myclue_recycleview2)

        recyclerViewA.layoutManager = layoutManager1
        recyclerViewB.layoutManager = layoutManager2

        val adapterA = MyClueInfoAdapterA(infolistA)
        val adapterB = MyClueInfoAdapterB(infolistB)
        recyclerViewA.adapter = adapterA
        recyclerViewB.adapter=adapterB
        Log.d(tag, "recycle_my_clue")
    }

    private fun initInfo(uid:Int) {
        //加载recycleview中的信息 我的寻人信息
        val pListA = getMyClueListA(uid)
        for(p in pListA){
            infolistA.add(myfindMsg(p.pid,p.name,p.date,p.image[0],p.location,p.status))
        }

        //加载recycleview2中的信息 我的寻亲信息
        val pListB = getMyClueListB(uid)
        for(p in pListB){
            infolistB.add(myfindMsg(p.pid,p.name,p.date,p.image[0],p.location,p.status))
        }
    }

    private fun getMyClueListA(uid :Int):ArrayList<Person>
    =if(Mode.isLocal()) getMyClueListALocal(uid)
    else getMyClueListARemote(uid)

    private fun getMyClueListB(uid :Int):ArrayList<Person>
    =if(Mode.isLocal()) getMyClueListBLocal(uid)
    else getMyClueListBRemote(uid)

}