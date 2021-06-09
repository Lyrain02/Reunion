package com.example.mytest.ui.myinfo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.R
import com.example.mytest.initialActivity
import com.example.mytest.ui.myclue.MyClueActivity
import com.example.mytest.ui.myfind.MyFindActivity
import com.example.mytest.ui.myrelative.MyRelativeActivity
import com.example.mytest.ui.myresult.MatchResultActivity


class MyinfoFragment : Fragment() {

    private lateinit var sendViewModel: MyinfoViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        sendViewModel =
                ViewModelProvider(this).get(MyinfoViewModel::class.java)

        
        val root = inflater.inflate(R.layout.fragment_myinfo, container, false)
        val intoMyinfo:LinearLayout = root.findViewById(R.id.myinfo_row)//我的信息
        val intoMyclue:LinearLayout=root.findViewById(R.id.myclue_row)//我的线索
        val intoMyfind:LinearLayout=root.findViewById(R.id.myfind_row)//我的寻人信息
        val intoMyrelative:LinearLayout=root.findViewById(R.id.myrelative_row)//我的寻亲信息
//        val intoHelp:ImageView=root.findViewById(R.id.into_help)//帮助 功能赘余
        val logout:TextView = root.findViewById(R.id.logout_myinfo)//登出


        /* 函数：我的信息页面跳转*/
        intoMyinfo.setOnClickListener(){
            val intent = Intent(activity, MatchResultActivity::class.java)
            startActivity(intent)
        }
       /* 函数：我的寻人信息页面跳转*/
        intoMyfind.setOnClickListener(){
            val intent = Intent(activity, MyFindActivity::class.java)
            startActivity(intent)
        }
        /*函数：登出页面跳转*/
        logout.setOnClickListener(){
            val intent = Intent(activity, initialActivity::class.java)
            startActivity(intent)
        }
        /*函数：我的寻亲页面跳转*/
        intoMyrelative.setOnClickListener(){
            val intent = Intent(activity, MyRelativeActivity::class.java)
            startActivity(intent)
        }
        /*函数：我的线索页面跳转*/
        intoMyclue.setOnClickListener(){
            val intent = Intent(activity, MyClueActivity::class.java)
            startActivity(intent)
        }




        return root
    }
}