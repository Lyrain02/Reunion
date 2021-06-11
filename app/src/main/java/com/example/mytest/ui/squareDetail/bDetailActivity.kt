package com.example.mytest.ui.squareDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytest.R
import com.example.mytest.user.Data
import com.example.mytest.user.Person
import com.example.mytest.user.PersonClue
import com.example.mytest.user.User

class bDetailActivity : AppCompatActivity() {
    val tag = "bDetailActivity"
    private val imageList = ArrayList<Int>()
    private val clueList = ArrayList<Clue>()
    private lateinit var viewHolder:ViewHolder
    private var pid = 0
    private lateinit var person:Person

    inner class ViewHolder{
        val p_name = findViewById<TextView>(R.id.p_nameB)
        val p_location = findViewById<TextView>(R.id.p_locationB)
        val p_date = findViewById<TextView>(R.id.p_dateB)
        val p_age = findViewById<TextView>(R.id.p_ageB)
        val p_sex = findViewById<TextView>(R.id.p_sexB)
        val p_blood = findViewById<TextView>(R.id.p_bloodB)
        val p_height = findViewById<TextView>(R.id.p_heightB)
        val p_weight = findViewById<TextView>(R.id.p_weightB)
        val p_appearance = findViewById<TextView>(R.id.p_appearanceB)
        val p_other = findViewById<TextView>(R.id.p_otherB)
        val p_status = findViewById<TextView>(R.id.p_stateB)
        val p_pdate = findViewById<TextView>(R.id.p_pdateB)
        val l_name = findViewById<TextView>(R.id.s_name)
        val l_tel = findViewById<TextView>(R.id.s_tel)
        val l_address = findViewById<TextView>(R.id.s_address)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_detail)
        supportActionBar?.hide()
        val viewHolder = ViewHolder()

        val bundle = intent.extras
        pid = bundle?.getInt("pid") ?:0 //???????????
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



        val submitClueB = findViewById<Button>(R.id.submintClueB)
        submitClueB.setOnClickListener {
            showInput()
        }

    }

    private fun initImages(images: ArrayList<Int>) {
        for(i in images){
            imageList.add(i)
        }
    }

    private fun initClue(pid:Int){
        val pclues = getPersonClueB(pid)
        for(i in pclues){
            clueList.add(Clue(getUserName(i.uid),getUserImage(i.uid),i.date,i.msg))
        }
    }

    private fun initPerson(pid:Int, viewHolder: ViewHolder){
        person = getPersonInfoB(pid)

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
        viewHolder.p_pdate.text = person.pdate
        viewHolder.p_status.text = when(person.status){
            -1 -> "已撤回"
            0 -> "未找到"
            1 -> "已找到"
            else -> "Error"
        }
        viewHolder.l_name.text =person.s_name
        viewHolder.l_tel.text = person.s_phone
        viewHolder.l_address.text = person.s_address

        initImages(person.image)
        initClue(pid)
    }

    private fun submitClueB(clue: String?):Boolean{
        Log.d(tag,"submit func!")
        return if(clue!=null) {
            val pclue = PersonClue(pid,User.id,getCurrentTime(),clue)
            if(clue.length!=0 && addClueB(pclue)) {
                Data.B_List[pid].clues.add(pclue)//本地存储
                if(person !in Data.my_ListClueB)
                    Data.my_ListClueB.add(person)//本地存储
                clueList.add(Clue(getUserName(pclue.uid), getUserImage(pclue.uid), getCurrentTime(), clue ?: ""))
                Log.d(tag,"submit successful")
                true
            }else false
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
                if(submitClueB(msg))
                    Toast.makeText(this.applicationContext,"提交成功",Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this.applicationContext,"提交失败",Toast.LENGTH_LONG).show()
            }
        builder.create().show()
        return msg
    }


    private fun addClueB(pclue: PersonClue):Boolean{
        //连接数据库[A表]
        //将PersonClue上传到数据库
        return true
    }

    private fun getPersonInfoB(pid:Int):Person{
        //连接数据库 [A表]
        //[A表]给定pid，获取人员信息，返回类型为Person
        return Data.B_List[pid]
    }

    private fun getPersonClueB(pid:Int):ArrayList<PersonClue>{
        //数据库连接 [A表]
        //[A表]给定pid，获取clue的列表，返回值为ArrayList<PersonClue>
        return Data.B_List[pid].clues
    }

    private fun getUserName(uid:Int):String{
        //连接数据库
        //通过用户id获取用户名
        return Data.userList[uid].name
    }

    private fun getUserImage(uid:Int):Int{
        //连接数据库
        //通过用户id获取用户头像
        return Data.userList[uid].image
    }

    private fun getCurrentTime():String{
        //非数据库连接部分，可以暂时不实现
        //获取发布时间
        return "2021年6月11日"
    }
}