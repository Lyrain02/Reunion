package com.example.mytest.ui.myfind

import android.content.DialogInterface
import android.content.Intent
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
import com.example.mytest.ui.myresult.MatchResultActivity
import com.example.mytest.ui.squareDetail.Clue
import com.example.mytest.ui.squareDetail.ClueAdapter
import com.example.mytest.ui.squareDetail.ImageAdapter
import com.example.mytest.user.*
import com.example.mytest.utils.Local.getPersonClueALocal
import com.example.mytest.utils.Local.getPersonInfoALocal
import com.example.mytest.utils.Local.getUserImageLocal
import com.example.mytest.utils.Local.getUserNameLocal
import com.example.mytest.utils.Local.updateStatusALocal
import com.example.mytest.utils.Remote.getPersonClueARemote
import com.example.mytest.utils.Remote.getPersonInfoARemote
import com.example.mytest.utils.Remote.getUserImageRemote
import com.example.mytest.utils.Remote.getUserNameRemote
import com.example.mytest.utils.Remote.updateStatusARemote

class MyFindDetailActivity : AppCompatActivity() {
    val tag = "MyFindDetailActivity"
    private val imageList = ArrayList<Int>()
    private val clueList = ArrayList<Clue>()
    private lateinit var viewHolder:ViewHolder
    private var pid = 0

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
        val p_status = findViewById<TextView>(R.id.p_stateA)
        val p_pdate = findViewById<TextView>(R.id.p_pdateA)
        val l_name = findViewById<TextView>(R.id.l_name)
        val l_tel = findViewById<TextView>(R.id.l_tel)
        val l_address = findViewById<TextView>(R.id.l_address)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_find_detail)
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

        val matchA = findViewById<Button>(R.id.btn_matchA)
        matchA.setOnClickListener {
            val intent  = Intent(this,MatchResultActivity::class.java)
            intent.putExtra("pid",pid)
            intent.putExtra("table","A")
            startActivity(intent)
        }

        val changeStatusA = findViewById<Button>(R.id.btn_changeStatusA)
        changeStatusA.setOnClickListener {
            showSingSelect()
        }

    }

    private fun initImages(images: ArrayList<Int>) {
        for(i in images){
            imageList.add(i)
        }
    }

    private fun initClue(pid:Int){
        val pclues = getPersonClueA(pid)
        for(i in pclues){
            clueList.add(Clue(getUserName(i.uid),getUserImage(i.uid),i.date,i.msg))
        }
    }

    private fun initPerson(pid:Int, viewHolder: ViewHolder){
        val person = getPersonInfoA(pid)

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
            -1 -> "?????????"
            0 -> "?????????"
            1 -> "?????????"
            else -> "Error"
        }
        viewHolder.l_name.text =person.s_name
        viewHolder.l_tel.text = person.s_phone
        viewHolder.l_address.text = person.s_address

        initImages(person.image)
        initClue(pid)
    }

    private fun getPersonInfoA(pid:Int):Person
    = if(Mode.isLocal()) getPersonInfoALocal(pid)
    else getPersonInfoARemote(pid)

    private fun getPersonClueA(pid:Int):ArrayList<PersonClue>
    = if(Mode.isLocal()) getPersonClueALocal(pid)
    else getPersonClueARemote(pid)

    private fun getUserName(uid:Int):String
    =if(Mode.isLocal()) getUserNameLocal(uid)
    else getUserNameRemote(uid)

    private fun getUserImage(uid:Int):Int
    =if(Mode.isLocal()) getUserImageLocal(uid)
    else getUserImageRemote(uid)

    private fun showSingSelect() {
        val items = arrayOf("????????????", "?????????", "?????????")
        var choice = -1
        val builder = AlertDialog.Builder(this).setTitle("????????????????????????")
            .setSingleChoiceItems(items, 1,
                DialogInterface.OnClickListener { dialogInterface, i -> choice = i })
            .setPositiveButton("??????",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    if (choice !== -1) {
                        val status = choice-1
                        updateStatusA(pid,status)
                        Toast.makeText(this, "?????????????????? ${items[choice]}", Toast.LENGTH_LONG).show()
                    }
                })
        builder.create().show()
    }

    private fun updateStatusA(pid:Int,status: Int):Boolean
    =if(Mode.isLocal()) updateStatusALocal(pid,status)
    else updateStatusARemote(pid,status)

}