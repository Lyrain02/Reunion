package com.example.mytest.ui.send

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.R
import com.example.mytest.ui.submit.Submit2Activity
import com.example.mytest.ui.submit.Submit1Activity


class SendFragment : Fragment() {

    private lateinit var dashboardViewModel: SendViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(SendViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val text1:TextView= root.findViewById(R.id.send_submit1)
        val text2:TextView =root.findViewById(R.id.send_submit2)
        text1.setOnClickListener(){
            val intent = Intent(activity, Submit1Activity::class.java)
            startActivity(intent)
        }
        text2.setOnClickListener(){
            val intent = Intent(activity, Submit2Activity::class.java)
            startActivity(intent)
        }

        return root
    }
    }

    
    

