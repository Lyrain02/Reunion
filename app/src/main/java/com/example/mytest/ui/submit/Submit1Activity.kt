package com.example.mytest.ui.submit

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mytest.R
import com.lljjcoder.citypickerview.widget.CityPicker
import com.lljjcoder.citypickerview.widget.CityPicker.OnCityItemClickListener
import java.util.*


class Submit1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit1)
        supportActionBar?.hide()
        val appearance: TextView = findViewById(R.id.appearance_submit1)//相貌特征的文本框
        val else_info_textview: TextView = findViewById(R.id.else_info_submit1)//其他信息的文本框
        val spinner: Spinner = findViewById(R.id.spinner_submit1)//血型的spinner
        val mBtn: Button = findViewById(R.id.tv_btn)//选择地址的按钮
        val mContent: TextView = findViewById(R.id.tv_content)//选择地址后显示的文本框
        val mdatepicker:TextView = findViewById(R.id.date_submit1)//选择失踪日期的文本框
        val mtimepicker:TextView = findViewById(R.id.time_submit1)//选择失踪时间的文本框


        /*函数：失踪日期的对话框跳转*/
        mdatepicker.setOnClickListener(){
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
       mtimepicker.setOnClickListener(){
           val ca = Calendar.getInstance()
           var mHour = ca[Calendar.HOUR_OF_DAY]
           var mMinute = ca[Calendar.MINUTE]

           val timePickerDialog = TimePickerDialog(
               this,
               TimePickerDialog.OnTimeSetListener{_, hourOfDay, minute ->
                   mHour   = hourOfDay
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
                    TimePickerDialog.OnTimeSetListener{_, hourOfDay, minute ->
                        mHour   = hourOfDay
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

}

//废物代码：
//mtimepicker.setOnClickListener(View.OnClickListener() {
//           fun onClick() {
//               val c = Calendar.getInstance()
//               c.setTimeInMillis(System.currentTimeMillis())
//               val hour: Int = c.get(Calendar.HOUR_OF_DAY)
//               val minute: Int = c.get(Calendar.MINUTE)
//               TimePickerDialog(
//                   this,
//                   OnTimeSetListener { view, hourOfDay, minute ->
//                       c.setTimeInMillis(System.currentTimeMillis())
//                       c.set(Calendar.HOUR_OF_DAY, hourOfDay)
//                       c.set(Calendar.MINUTE, minute)
//                       c.set(Calendar.SECOND, 0)
//                       c.set(Calendar.MILLISECOND, 0)
//                       Toast.makeText(
//                           this,
//                           c.get(Calendar.HOUR_OF_DAY).toString() + ":" + c.get(Calendar.MINUTE),
//                           Toast.LENGTH_SHORT
//                       ).show()
//                   }, hour, minute, true
//               ).show()
//               mtimepicker.text=c.get(Calendar.HOUR_OF_DAY).toString() + ":" + c.get(Calendar.MINUTE)
//
//
//           }
//       })
