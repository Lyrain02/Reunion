package com.example.mytest.ui.myfind

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.MainActivity
import com.example.mytest.R
import com.example.mytest.ui.square.Easy1Msg
import com.example.mytest.ui.square.Info1Adapter
import com.example.mytest.ui.squareDetail.aDetailActivity
import kotlin.coroutines.coroutineContext

class myFindInfoAdapter (val infolist:List<myfindMsg>) : RecyclerView.Adapter<myFindInfoAdapter.ViewHolder>(){
    private val tag = "myFindInfoAdapter"
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val pimage: ImageView =view.findViewById(R.id.pfind_image)
        val pname: TextView = view.findViewById(R.id.pfind_name)
        val pdate: TextView = view.findViewById(R.id.pfind_date)
        val pplace: TextView =view.findViewById(R.id.pfind_place)
        val pstatus:TextView=view.findViewById(R.id.myfind_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.find_info, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val p = infolist[viewHolder.adapterPosition]
            val intent = Intent(view.context, MyFindDetailActivity::class.java)
            intent.putExtra("pid",p.pid)
            view.context.startActivity(intent)
        }
        return viewHolder
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
