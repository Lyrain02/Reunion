package com.example.mytest.ui.square

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.Api
import com.example.mytest.R
import com.example.mytest.Util

//B类广场瀑布流recycleview
class Info2Adapter (val infolist:List<Easy1Msg>) : RecyclerView.Adapter<Info2Adapter.ViewHolder>(){

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val pimage: ImageView =view.findViewById(R.id.pimage)
        val pname: TextView = view.findViewById(R.id.pname)
        val pdate: TextView = view.findViewById(R.id.pdate)
        val pplace: TextView =view.findViewById(R.id.pplace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.easy_info, parent, false)
        val viewHolder = ViewHolder(view)

        Log.d("aa","Cycle")
        viewHolder.itemView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
//                val position = viewHolder.adapterPosition
                Log.d("aaa",viewHolder.adapterPosition.toString())
            }
        })

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = infolist[position]
        if(info.imageId==R.drawable.eg_girl||info.imageId==R.drawable.eg_boy)
            holder.pimage.setImageResource(info.imageId)//显示
        else{
            var getImage = ""
            val t = Thread {
                getImage = Api.get_image(info.imageId)
            }
            t.start()
            t.join()
            holder.pimage.setImageBitmap(Util.load(getImage))
        }
        holder.pname.text=info.name
        holder.pplace.text=info.place
        holder.pdate.text=info.datetime

    }
    override fun getItemCount() = infolist.size





}

