package com.example.mytest.ui.squareDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.mytest.R
import com.example.mytest.ui.square.Easy1Msg
import com.example.mytest.user.LostPerson
import com.example.mytest.user.Person
import com.example.mytest.user.SavePerson
import com.example.mytest.user.User
import java.lang.StringBuilder

class aDetailActivity : AppCompatActivity() {
    val tag = "aDetailActivity"
    private val imageList = ArrayList<Int>()
    private val clueList = ArrayList<Clue>()
    private lateinit var viewHolder:ViewHolder

    inner class ViewHolder{
        val p_name = findViewById<TextView>(R.id.p_nameA)
        val p_location = findViewById<TextView>(R.id.p_locationA)
        val p_date = findViewById<TextView>(R.id.p_dateA)
        val p_age = findViewById<TextView>(R.id.p_ageA)
        val p_sex = findViewById<TextView>(R.id.p_sexA)
        val p_blood = findViewById<TextView>(R.id.p_bloodA)
        val p_height = findViewById<TextView>(R.id.p_heightA)
        val p_weight = findViewById<TextView>(R.id.p_weightA)
        val p_appearance = findViewById<TextView>(R.id.p_appearanceA)
        val p_other = findViewById<TextView>(R.id.p_otherA)
        val l_name = findViewById<TextView>(R.id.l_name)
        val l_tel = findViewById<TextView>(R.id.l_tel)
        val l_address = findViewById<TextView>(R.id.l_address)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_detail)
        supportActionBar?.hide()
        val viewHolder = ViewHolder()

        val bundle = intent.extras
        val pid = bundle?.getInt("pid") ?:0
        Log.d(tag,"person's id is $pid")

        initPerson(pid,viewHolder)

        val recyclerView1 = findViewById<RecyclerView>(R.id.imageRecyclerView)
        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation =LinearLayoutManager.HORIZONTAL
        recyclerView1.layoutManager = layoutManager1
        val imageAdapter = ImageAdapter(imageList)
        recyclerView1.adapter =imageAdapter

        val recyclerView2 = findViewById<RecyclerView>(R.id.clueRecyclerView)
        val layoutManager2 = LinearLayoutManager(this)
        recyclerView2.layoutManager = layoutManager2
        val clueAdapter = ClueAdapter(clueList)
        recyclerView2.adapter =clueAdapter

        val submitClueA = findViewById<Button>(R.id.submintClueA)
        submitClueA.setOnClickListener {
            val clue = showInput()
        }

    }
    private fun initImages() {
        //数据库连接
        repeat(1){
            imageList.add((R.drawable.lhp))
            imageList.add((R.drawable.ldl))
            imageList.add((R.drawable.pq))
            imageList.add((R.drawable.yqy))
            imageList.add((R.drawable.yxx))
            imageList.add((R.drawable.zj))
        }
    }
    private fun initClue(){
        //数据库连接
        repeat(1){
            clueList.add(Clue("Sam",R.drawable.eg_boy,"2020年10月11日","两周前曾经在长风公园湖边看到他"))
            clueList.add(Clue("Alice",R.drawable.eg_girl,"2021年1月11日","似乎在中山公园地铁站附近见到过他"))
        }
    }
    private fun initPerson(pid:Int, viewHolder: ViewHolder){
        val person = Person("姜宁康","上海市","2010年1月17日","30-40岁","男性",
                "A型","1米8","60kg","英俊潇洒","非常好的一个人")
        val sPerson = SavePerson("华东师范大学","86-21-62233333","上海市普陀区中山北路3663号")

        viewHolder.p_name.text = person.name
        viewHolder.p_location.text =person.location
        viewHolder.p_date.text = person.date
        viewHolder.p_age.text = person.age
        viewHolder.p_sex.text =person.sex
        viewHolder.p_blood.text = person.blood
        viewHolder.p_height.text = person.height
        viewHolder.p_weight.text = person.weight
        viewHolder.p_appearance.text = person.appearance
        viewHolder.p_other.text = person.other

        viewHolder.l_name.text =sPerson.name
        viewHolder.l_tel.text = sPerson.tel
        viewHolder.l_address.text = sPerson.address

        initImages()
        initClue()
    }

    private fun submitClueA(clue: String?):Boolean{
        Log.d(tag,"submit func!")
        //提交线索+表id+用户id
        //调用submitclue（uid，tid，clue）
        return if(clue!=null) {
            clueList.add(Clue(User.name, R.drawable.eg_girl, "2021年6月11日", clue?:""))
            Log.d(tag,"submit successful")
            true
        }else{
            Log.d(tag,"submit failure")
            false
        }
    }

    private fun showInput():String? {
        val editText = EditText(this)
        var msg:String?=null
        val builder = AlertDialog.Builder(this).setTitle("请输入线索：").setView(editText)
                .setPositiveButton(
                        "确定"
                ) { dialogInterface, i ->
                    msg = editText.text.toString().trim()
                    Log.d(tag,"clue is $msg")
                    submitClueA(msg)
                }
        builder.create().show()
        return msg
    }
}