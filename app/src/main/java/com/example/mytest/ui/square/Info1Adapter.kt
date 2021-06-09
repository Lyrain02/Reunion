package com.example.mytest.ui.square

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.R

/*A类广场瀑布流的recycleview适配器*/
class Info1Adapter(val infolist:List<Easy1Msg>) :RecyclerView.Adapter<Info1Adapter.ViewHolder>(){
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val pimage:ImageView=view.findViewById(R.id.pimage)
        val pname:TextView = view.findViewById(R.id.pname)
        val pdate:TextView = view.findViewById(R.id.pdate)
        val pplace:TextView=view.findViewById(R.id.pplace)

    }
    interface OnItemClickListener {
        fun OnItemClick(view: View?, data: Easy1Msg?)
    }



    //需要外部访问，所以需要设置set方法，方便调用
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        Log.d("aaaa","clickListen")
    }

//    函数：加载recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.easy_info, parent, false)
        val viewHolder = ViewHolder(view)
        Log.d("aa","Cycle")
        return ViewHolder(view)
    }
//    将数据和recyclerview绑定
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = infolist[position]

        holder.pimage.setImageResource(info.imageId)
        holder.pname.text=info.name
        holder.pplace.text=info.place
        holder.pdate.text=info.datetime

    }
    override fun getItemCount() = infolist.size


}

