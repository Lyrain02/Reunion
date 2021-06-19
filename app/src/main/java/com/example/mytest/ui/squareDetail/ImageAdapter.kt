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

class ImageAdapter(val imageList: List<Int>) : //FruitAdaptor参数是data
        RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) { //ViewHolder参数是view
        //和layout中的控件一一对应
        val p_image: ImageView = view.findViewById(R.id.p_image)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //获取view，创建ViewHolder
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_item, parent, false)
        val viewHolder = ViewHolder(view)
//        viewHolder.itemView.setOnClickListener {
//            val fruit = fruitList[viewHolder.adapterPosition]
//            Toast.makeText(parent.context,"you click view ${fruit.name}",Toast.LENGTH_SHORT).show()
//        }
        return viewHolder
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //为ViewHoler绑定内容
        val img = imageList[position] //获取数据
        if(img==R.drawable.eg_girl||img==R.drawable.eg_boy)
            holder.p_image.setImageResource(img)//显示
        else{
            var getImage = ""
            val t = Thread {
                getImage = Api.get_image(img)
            }
            t.start()
            t.join()
            holder.p_image.setImageBitmap(Util.load(getImage))
        }
    }
    override fun getItemCount() = imageList.size
}