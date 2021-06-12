package com.example.mytest.ui.submit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.mytest.R
import com.example.mytest.user.Data
import com.example.mytest.user.Person
import com.lljjcoder.citypickerview.widget.CityPicker
import com.lljjcoder.citypickerview.widget.CityPicker.OnCityItemClickListener
import java.util.*
import kotlin.collections.ArrayList


class Submit1Activity : AppCompatActivity() {
    var totblood = arrayOf("A型","B型","AB型","O型","其他")
    private val imageList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit1)
        supportActionBar?.hide()
        val pname: TextView = findViewById(R.id.name_submit1)//姓名
        val mContent: TextView = findViewById(R.id.tv_content)//选择地址后显示的文本框
        val detailadress: TextView = findViewById(R.id.detail_place_submit1)//详细地址
        val mdatepicker: TextView = findViewById(R.id.date_submit1)//选择失踪日期的文本框
        val mtimepicker: TextView = findViewById(R.id.time_submit1)//选择失踪时间的文本框
        val page: TextView = findViewById(R.id.age_submit1)//年龄

        val psexgroup: RadioGroup = findViewById(R.id.radioGroup1)//选择性别的group
        val pmalebutton: RadioButton = findViewById(R.id.radio1_button_1)//男
        val pfemalebutton: RadioButton = findViewById(R.id.radio1_button_2)//女
        val punknownbutton: RadioButton = findViewById(R.id.radio1_button_3)//未知
        var gender: String = "未知"
        val spinner: Spinner = findViewById(R.id.spinner_submit1)//血型的spinner
        var spinnerval:String="A型"
        val ptall: TextView = findViewById(R.id.tall_submit1)//身高
        val pshape: TextView = findViewById(R.id.shape_submit1)//体型
        val appearance: TextView = findViewById(R.id.appearance_submit1)//相貌特征的文本框
        val else_info_textview: TextView = findViewById(R.id.else_info_submit1)//其他信息的文本框
        val loadImage = findViewById<TextView>(R.id.loadImage)//上传照片

        val linkmanName: TextView = findViewById(R.id.linkmanName_submit1)//联系人名字
        val linkmanTell: TextView = findViewById(R.id.linkmanTelephone_submit1)//联系人电话
        val linkmanpostcode: TextView = findViewById(R.id.linkmanPostcode_submit1)//联系人邮编
        val linkmanaddress: TextView = findViewById(R.id.linkmanAddress_submit1)//联系人地址
        val linkmanrelationship: TextView = findViewById(R.id.linkmanRelationship_submit1)//联系人亲属关系
        val mBtn: Button = findViewById(R.id.tv_btn)//选择地址的按钮

        val submitbutton: Button = findViewById(R.id.submitButton_submit1)//提交公告内容


        /*函数：radioGroup获取性别值*/
        psexgroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
                if (checkedId == R.id.radio1_button_1) {
                    gender = "男"
                } else if (checkedId == R.id.radio1_button_2) {
                    gender = "女"
                } else {
                    gender = "未知"
                }
            }
        })

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            //选择item的选择点击监听事件
            override fun onItemSelected(
                arg0: AdapterView<*>?, arg1: View,
                arg2: Int, arg3: Long
            ) {
                spinnerval=totblood[arg2]
            }
            override fun onNothingSelected(arg0: AdapterView<*>?) {

            }
        })

        loadImage.setOnClickListener {
            val imageSrc = uploadImage()
            imageList.add(imageSrc)
            Toast.makeText(this.applicationContext,"上传一张图片",Toast.LENGTH_LONG).show()
            Log.d("Submit1","upload image")
        }

        /*函数：提交公告*/
        submitbutton.setOnClickListener() {
            val person = Person()
            person.name = pname.text.toString()
            person.location = mContent.text.toString() + " " + detailadress.text.toString()
            person.date = mdatepicker.text.toString() + " " + mtimepicker.text.toString()
            person.age = page.text.toString() + "岁"

            if(gender.equals("男")){
                person.sex=Person.SEX_MALE
            }
            else if(gender.equals("女")){
                person.sex=Person.SEX_FEMALE
            }
            else person.sex=Person.SEX_OTHER

            when(spinnerval){
                "A型"->{
                    person.blood=Person.BLOOD_A
                }
                "AB型"->{
                    person.blood=Person.BLOOD_AB
                }
                "B型"->{
                    person.blood=Person.BLOOD_B
                }
                "O型"->{
                    person.blood=Person.BLOOD_O
                }
                "其他"->{
                    person.blood=Person.BLOOD_OTHER
                }
            }

            person.height = ptall.toString() +"cm"
            person.weight = pshape.text.toString()+"kg"
            person.appearance = appearance.text.toString()
            person.other = else_info_textview.text.toString()
            person.pdate = getCurrentTime()
            for(i in imageList){
                person.image.add(i)
            }

            person.s_name = linkmanName.text.toString()
            person.s_phone = linkmanTell.text.toString()
            person.s_postcode = linkmanpostcode.text.toString()
            person.s_address = linkmanaddress.text.toString()
            person.s_relative = linkmanaddress.text.toString()
            Log.d("Submit1Activity", person.name + person.location + person.sex + person.date+person.blood)

            person.pid = Data.A_List.size//本地存储
            Data.A_List.add(person)//本地存储，广场
            Data.my_ListA.add(person)//本地存储，个人发布
            if(person.image.size!=0&&uploadPersonB(person)){
                Toast.makeText(this.applicationContext,"提交成功",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this.applicationContext,"提交失败",Toast.LENGTH_LONG).show()
            }
        }


        /*函数：失踪日期的对话框跳转*/
        mdatepicker.setOnClickListener() {
            val ca = Calendar.getInstance()
            var mYear = ca[Calendar.YEAR]
            var mMonth = ca[Calendar.MONTH]
            var mDay = ca[Calendar.DAY_OF_MONTH]

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    mYear = year
                    mMonth = month
                    mDay = dayOfMonth
                    val mDate = "${year}/${month + 1}/${dayOfMonth}"
                    // 将选择的日期赋值给TextView
                    findViewById<TextView>(R.id.date_submit1).text = mDate
                },
                mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }
        /* 函数：失踪时间的选择时间对话框*/
        mtimepicker.setOnClickListener() {
            val ca = Calendar.getInstance()
            var mHour = ca[Calendar.HOUR_OF_DAY]
            var mMinute = ca[Calendar.MINUTE]

            val timePickerDialog = TimePickerDialog(
                this,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    mHour = hourOfDay
                    mMinute = minute
                    val mTime = "${hourOfDay}:${minute}"
                    findViewById<TextView>(R.id.time_submit1).text = mTime
                },
                mHour, mMinute, true
            )
            timePickerDialog.show()
        }


//
        /*函数：设置EditText的显示方式为多行文本输入*/
        setMultipleLine(else_info_textview)
        setMultipleLine(appearance)

        /*操作：绑定血型的选项*/
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_submit1,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }


        /* 操作：显示用户选择的地址在文本框中*/
        mBtn.setOnClickListener() {
            Log.d("aaa", "click")
            getAdress(mContent)
        }

    }

    fun buttonFunc(view: View) {
        when (view.id) {
            R.id.date_submit1 -> {
                // 日期选择器
                val ca = Calendar.getInstance()
                var mYear = ca[Calendar.YEAR]
                var mMonth = ca[Calendar.MONTH]
                var mDay = ca[Calendar.DAY_OF_MONTH]

                val datePickerDialog = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                        mYear = year
                        mMonth = month
                        mDay = dayOfMonth
                        val mDate = "${year}/${month + 1}/${dayOfMonth}"
                        // 将选择的日期赋值给TextView
                        findViewById<TextView>(R.id.date_submit1).text = mDate
                    },
                    mYear, mMonth, mDay
                )
                datePickerDialog.show()
            }
            R.id.time_submit1 -> {
                // 时间选择器
                val ca = Calendar.getInstance()
                var mHour = ca[Calendar.HOUR_OF_DAY]
                var mMinute = ca[Calendar.MINUTE]

                val timePickerDialog = TimePickerDialog(
                    this,
                    TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                        mHour = hourOfDay
                        mMinute = minute
                        val mTime = "${hourOfDay}:${minute}"
                        findViewById<TextView>(R.id.time_submit1).text = mTime
                    },
                    mHour, mMinute, true
                )
                timePickerDialog.show()
            }
        }
    }

    private fun setMultipleLine(textView: TextView) {
        textView.inputType = InputType.TYPE_TEXT_FLAG_MULTI_LINE
        textView.gravity = Gravity.TOP//文本显示的位置在EditText的最上方
        textView.isSingleLine = false  //改变默认的单行模式
        textView.setHorizontallyScrolling(false)//水平滚动设置为False
    }

    /* 函数：显示用户选择的地址在文本框中*/
    fun getAdress(mContent: TextView) {
        //默认下的地址选择
        val cityPicker: CityPicker = CityPicker.Builder(this)
            .textSize(14)
            .title("地址选择")
            .titleBackgroundColor("#FFFFFF")
            .confirTextColor("#696969")
            .cancelTextColor("#696969")
            .province("江苏省")
            .city("常州市")
            .district("天宁区")
            .textColor(Color.parseColor("#000000"))
            .provinceCyclic(true)
            .cityCyclic(false)
            .districtCyclic(false)
            .visibleItemsCount(7)
            .itemPadding(10)
            .onlyShowProvinceAndCity(false)
            .build()
        cityPicker.show()

        cityPicker.setOnCityItemClickListener(OnCityItemClickListener { citySelected -> //省份
            val province = citySelected[0]
            //城市
            val city = citySelected[1]
            //区县（如果设定了两级联动，那么该项返回空）
            val district = citySelected[2]
            //邮编
            val code = citySelected[3]
            //为展示区赋值
            mContent.text = "  " +
                    province.trim { it <= ' ' } + "-" + city.trim { it <= ' ' } + "-" + district.trim { it <= ' ' } + "   "
        })
    }

    private fun uploadImage():Int{
        // 上传照片，并返回照片存储的结果Int
        return R.drawable.eg_girl
    }

    private fun uploadPersonB(p:Person):Boolean{
        //连接数据库[A表]
        //上传Person类的数据，需要生成pid
        return true
    }

    private fun getCurrentTime():String{
        //获取发布时间
        return "2021年6月11日"
    }

}
