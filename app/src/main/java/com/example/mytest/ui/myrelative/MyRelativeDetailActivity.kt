package com.example.mytest.ui.myrelative

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
import com.example.mytest.user.Data
import com.example.mytest.user.Mode
import com.example.mytest.user.Person
import com.example.mytest.user.PersonClue
import com.example.mytest.utils.Local.getPersonClueBLocal
import com.example.mytest.utils.Local.getPersonInfoBLocal
import com.example.mytest.utils.Local.getUserImageLocal
import com.example.mytest.utils.Local.getUserNameLocal
import com.example.mytest.utils.Local.updateStatusBLocal
import com.example.mytest.utils.Remote.getPersonClueBRemote
import com.example.mytest.utils.Remote.getPersonInfoBRemote
import com.example.mytest.utils.Remote.getUserImageRemote
import com.example.mytest.utils.Remote.getUserNameRemote
import com.example.mytest.utils.Remote.updateStatusBRemote


class MyRelativeDetailActivity : AppCompatActivity() {
    val tag = "RelativeDetailActivity"
    private val imageList = ArrayList<Int>()
    private val clueList = ArrayList<Clue>()
    private lateinit var viewHolder: ViewHolder
    private var pid = 0

    inner class ViewHolder {
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
        val s_name = findViewById<TextView>(R.id.s_name)
        val s_tel = findViewById<TextView>(R.id.s_tel)
        val s_address = findViewById<TextView>(R.id.s_address)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_relative_detail)
        supportActionBar?.hide()
        val viewHolder = ViewHolder()

        val bundle = intent.extras
        pid = bundle?.getInt("pid") ?: 0 //???????????
        Log.d(tag, "person's id is $pid")

        initPerson(pid, viewHolder)

        val recyclerView1 = findViewById<RecyclerView>(R.id.imageRecyclerView)
        val layoutManager1 = LinearLayoutManager(this)
        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView1.layoutManager = layoutManager1
        val imageAdapter = ImageAdapter(imageList)
        recyclerView1.adapter = imageAdapter

        val recyclerView2 = findViewById<RecyclerView>(R.id.clueRecyclerView)
        val layoutManager2 = LinearLayoutManager(this)
        recyclerView2.layoutManager = layoutManager2
        val clueAdapter = ClueAdapter(clueList)
        recyclerView2.adapter = clueAdapter

        val matchB = findViewById<Button>(R.id.btn_matchB)
        matchB.setOnClickListener {
            val intent = Intent(this, MatchResultActivity::class.java)
            intent.putExtra("pid", pid)
            intent.putExtra("table","B")
            startActivity(intent)
        }

        val changeStatusB = findViewById<Button>(R.id.btn_changeStatusB)
        changeStatusB.setOnClickListener {
            showSingSelect()
        }

    }

    private fun initImages(images: ArrayList<Int>) {
        for (i in images) {
            imageList.add(i)
        }
    }

    private fun initClue(pid: Int) {
        val pclues = getPersonClueB(pid)
        for (i in pclues) {
            clueList.add(Clue(getUserName(i.uid), getUserImage(i.uid), i.date, i.msg))
        }
    }

    private fun initPerson(pid: Int, viewHolder: ViewHolder) {
        val person = getPersonInfoB(pid)

        viewHolder.p_name.text = person.name
        viewHolder.p_location.text = person.location
        viewHolder.p_date.text = person.date
        viewHolder.p_age.text = person.age
        viewHolder.p_sex.text = person.sex
        viewHolder.p_blood.text = person.blood
        viewHolder.p_height.text = person.height
        viewHolder.p_weight.text = person.weight
        viewHolder.p_appearance.text = person.appearance
        viewHolder.p_other.text = person.other
        viewHolder.p_pdate.text = person.pdate
        viewHolder.p_status.text = when (person.status) {
            -1 -> "已撤回"
            0 -> "未找到"
            1 -> "已找到"
            else -> "Error"
        }
        viewHolder.s_name.text = person.s_name
        viewHolder.s_tel.text = person.s_phone
        viewHolder.s_address.text = person.s_address

        initImages(person.image)
        initClue(pid)
    }

    private fun getPersonInfoB(pid: Int): Person
    =if(Mode.isLocal()) getPersonInfoBLocal(pid)
    else getPersonInfoBRemote(pid)

    private fun getPersonClueB(pid: Int): ArrayList<PersonClue>
    =if(Mode.isLocal()) getPersonClueBLocal(pid)
    else getPersonClueBRemote(pid)

    private fun getUserName(uid: Int): String
    =if(Mode.isLocal()) getUserNameLocal(uid)
    else getUserNameRemote(uid)

    private fun getUserImage(uid: Int): Int
    =if(Mode.isLocal()) getUserImageLocal(uid)
    else getUserImageRemote(uid)


    private fun showSingSelect() {
        val items = arrayOf("撤销寻人", "未找到", "已找到")
        var choice = -1
        val builder = AlertDialog.Builder(this).setTitle("修改人员状态信息")
            .setSingleChoiceItems(items, 1,
                DialogInterface.OnClickListener { dialogInterface, i -> choice = i })
            .setPositiveButton("确定",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    if (choice !== -1) {
                        val status = choice - 1
                        updateStatusB(pid, status)
                        Toast.makeText(this, "状态修改为： ${items[choice]}", Toast.LENGTH_LONG).show()
                    }
                })
        builder.create().show()
    }

    private fun updateStatusB(pid: Int, status: Int): Boolean
    =if(Mode.isLocal()) updateStatusBLocal(pid,status)
    else updateStatusBRemote(pid,status)

}