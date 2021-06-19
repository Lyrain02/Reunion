package com.example.mytest.ui.squareDetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.Api
import com.example.mytest.R
import com.example.mytest.Util

class ClueAdapter(val clueList: List<Clue>) : //FruitAdaptor参数是data
    RecyclerView.Adapter<ClueAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { //ViewHolder参数是view
        //和layout中的控件一一对应
        val u_image: ImageView = view.findViewById(R.id.u_image)
        val u_name: TextView = view.findViewById(R.id.u_name)
        val clue_date: TextView = view.findViewById(R.id.clue_date)
        val clue_msg: TextView = view.findViewById(R.id.clue_msg)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //获取view，创建ViewHolder
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.clue_item, parent, false)
        val viewHolder = ViewHolder(view)
//        viewHolder.itemView.setOnClickListener {
//            val fruit = fruitList[viewHolder.adapterPosition]
//            Toast.makeText(parent.context,"you click view ${fruit.name}",Toast.LENGTH_SHORT).show()
//        }
        return viewHolder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //为ViewHoler绑定内容
        val clue = clueList[position] //获取数据
        if(clue.userimg==R.drawable.eg_girl||clue.userimg==R.drawable.eg_boy)
            holder.u_image.setImageResource(clue.userimg)//显示
        else{
            var getImage = ""
            val t = Thread {
                getImage = Api.get_image(clue.userimg)
            }
            t.start()
            t.join()
            holder.u_image.setImageBitmap(Util.load(getImage))
        }
        holder.u_name.text = clue.username
        holder.clue_date.text = clue.date
        holder.clue_msg.text = clue.msg
    }
    override fun getItemCount() = clueList.size
}