package com.example.mytest.ui.submit

//
//import com.example.mytest.R
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import android.widget.Toast
//import androidx.recyclerview.widget.RecyclerView
//import androidx.recyclerview.widget.RecyclerView.ViewHolder
//import com.example.mytest.ui.myfind.myFindInfoAdapter
//
//
//class SendBlogAdapter(context: Context, data: List<String>) :
//    RecyclerView.Adapter<SendBlogAdapter.ViewHolder>() {
//    private val context: Context = context
//    private val data: List<String>
//
//    class ViewHolder(view: View,lag:Int): RecyclerView.ViewHolder(view) {
//        val imageView: ImageView =view.findViewById(R.id.defaultimg)
//        val lag:Int=lag
//    }
//
//     override fun onCreateViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ):ViewHolder {
//        val view1 = LayoutInflater.from(parent.context)
//            .inflate(com.example.mytest.R.layout.grid_item, parent, false)
//        val view2 = LayoutInflater.from(parent.context)
//            .inflate(com.example.mytest.R.layout.send_item, parent, false)
//
//
//        if (viewType == ITEM_TWO) {
//            return ViewHolder(view1,1)
//        } else {
//            return ViewHolder(view2,2)
//        }
//
//    }
//
//    override fun onBindViewHolder(
//        holder: ViewHolder,
//        position: Int
//    ) {
//        if (holder is AnoViewHolder) {
//
//            holder.img.setOnClickListener(object :
//                View.OnClickListener {
//                override fun onClick(v: View?) {
//                    Toast.makeText(context, "im specatil", Toast.LENGTH_SHORT).show()
//                }
//            })
//        } else if (holder is ViewHolder) {
//            holder.imageView.setOnClickListener(object :
//                View.OnClickListener {
//                override fun onClick(v: View?) {
//                    Toast.makeText(context, "im nomal", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return data.size + 1
//    }
//
//
//    override fun getItemViewType(position: Int): Int {
//        return if (position > data.size - 1) {
//            ITEM_TWO
//        } else {
//            ITEM_ONE
//        }
//    }
//
//    companion object {
//        private const val ITEM_ONE = 1
//        private const val ITEM_TWO = 2
//    }
//
//    init {
//        this.data = data
//    }
//
//
//}
