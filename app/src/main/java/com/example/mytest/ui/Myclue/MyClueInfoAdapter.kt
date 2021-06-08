package com.example.mytest.ui.Myclue

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.R
import com.example.mytest.ui.myfind.myFindInfoAdapter
import com.example.mytest.ui.myfind.myfindMsg
import com.example.mytest.ui.square.Easy1Msg

class MyClueInfoAdapter (val infolist:List<myfindMsg>) : RecyclerView.Adapter<MyClueInfoAdapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val pimage: ImageView =view.findViewById(R.id.pfind_image)
        val pname: TextView = view.findViewById(R.id.pfind_name)
        val pdate: TextView = view.findViewById(R.id.pfind_date)
        val pplace: TextView =view.findViewById(R.id.pfind_place)
        val pstatus: TextView =view.findViewById(R.id.myfind_status)
    }
    interface OnItemClickListener {

        fun OnItemClick(view: View?, data: Easy1Msg?)
    }



    //需要外部访问，所以需要设置set方法，方便调用
    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        Log.d("aaaa","clickListen")
        //this.onItemClickListener = onItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.find_info, parent, false)
        val viewHolder = ViewHolder(view)
        Log.d("aa","Cycle")
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //绑定viewholder
        val info = infolist[position]

        holder.pimage.setImageResource(info.imageId)
        holder.pname.text=info.name
        holder.pplace.text=info.place
        holder.pdate.text=info.datetime
        val kk:Int = info.status
        when(kk){
            0 -> holder.pstatus.text = "未找到"
            -1 -> holder.pstatus.text= "已撤回"
            1 -> holder.pstatus.text = "已找到"
        }

    }
    override fun getItemCount() = infolist.size
}
