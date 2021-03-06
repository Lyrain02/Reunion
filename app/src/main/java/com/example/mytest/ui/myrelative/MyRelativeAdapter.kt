package com.example.mytest.ui.myrelative

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.Api
import com.example.mytest.R
import com.example.mytest.Util
import com.example.mytest.ui.myfind.MyFindDetailActivity
import com.example.mytest.ui.myfind.myfindMsg


class MyRelativeAdapter (val infolist:List<myfindMsg>) : RecyclerView.Adapter<MyRelativeAdapter.ViewHolder>(){
    private val tag = "MyRelativeAdapter"
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val pimage: ImageView =view.findViewById(R.id.pfind_image)
        val pname: TextView = view.findViewById(R.id.pfind_name)
        val pdate: TextView = view.findViewById(R.id.pfind_date)
        val pplace: TextView =view.findViewById(R.id.pfind_place)
        val pstatus: TextView =view.findViewById(R.id.myfind_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.find_info, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val p = infolist[viewHolder.adapterPosition]
            val intent = Intent(view.context, MyRelativeDetailActivity::class.java)
            intent.putExtra("pid",p.pid)
            view.context.startActivity(intent)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //绑定viewholder
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
        when(info.status){
            0 -> holder.pstatus.text = "未找到"
            -1 -> holder.pstatus.text= "已撤回"
            1 -> holder.pstatus.text = "已找到"
        }
    }

    override fun getItemCount() = infolist.size
}
