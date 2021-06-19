package com.example.mytest.ui.myinfo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mytest.*
import com.example.mytest.ui.Myclue.MyClueActivity
import com.example.mytest.ui.myfind.MyFindActivity
import com.example.mytest.ui.myrelative.MyRelativeActivity
import com.example.mytest.ui.myresult.MatchResultActivity
import com.example.mytest.user.Data
import com.example.mytest.user.Mode
import com.example.mytest.user.User
import com.example.mytest.utils.Local.authentificationLocal
import com.example.mytest.utils.Local.changeImageLocal
import com.example.mytest.utils.Local.updateUserImageLocal
import com.example.mytest.utils.Local.updateUserNameLocal
import com.example.mytest.utils.Remote.authentificationRemote
import com.example.mytest.utils.Remote.updateUserImageRemote
import com.example.mytest.utils.Remote.updateUserNameRemote


class MyinfoFragment : Fragment() {
    private val tag1 = "MyinfoFragment"
    private lateinit var sendViewModel: MyinfoViewModel
    private lateinit var viewHolder:ViewHolder

    inner class ViewHolder(val image : ImageView,val name:TextView,val auth:TextView)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        sendViewModel =
                ViewModelProvider(this).get(MyinfoViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_myinfo, container, false)
        val myinfo_image = root.findViewById<ImageView>(R.id.myinfo_image)
        val myinfo_name = root.findViewById<TextView>(R.id.myinfo_name)
        val reset_name = root.findViewById<ImageView>(R.id.reset_name)
        val myinfo_auth = root.findViewById<TextView>(R.id.myinfo_auth)
        viewHolder = ViewHolder(myinfo_image,myinfo_name,myinfo_auth)
        val intoMyinfo:LinearLayout = root.findViewById(R.id.myinfo_row)//我的信息
        val intoMyclue:LinearLayout=root.findViewById(R.id.myclue_row)//我的线索
        val intoMyfind:LinearLayout=root.findViewById(R.id.myfind_row)//我的寻人信息
        val intoMyrelative:LinearLayout=root.findViewById(R.id.myrelative_row)//我的寻亲信息
//        val intoHelp:ImageView=root.findViewById(R.id.into_help)//帮助 功能赘余
        val logout:TextView = root.findViewById(R.id.myinfo_logout)//登出

        initMyinfo(viewHolder)

        /*修改头像*/
        myinfo_image.setOnClickListener {
            var image = uploadImage()  //获取头像照片
            if(updateUserImage(User.id, image)) { //存储用户头像
                if(image==R.drawable.eg_girl||image==R.drawable.eg_boy)
                    myinfo_image.setImageResource(image)//显示
                else{
                    var getImage = ""
                    val t = Thread {
                        getImage = Api.get_image(image)
                    }
                    t.start()
                    t.join()
                    myinfo_image.setImageBitmap(Util.load(getImage))
                }
                User.image = image //User更新
                Toast.makeText(this.context,"更新成功",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this.context,"更新失败",Toast.LENGTH_LONG).show()
            }
            User.log()
        }
        /*修改用户名*/
        reset_name.setOnClickListener {
            resetName(viewHolder)
        }
        /*修改实名状态*/
        myinfo_auth.setOnClickListener{
            if(isAuthFinished(User.id)){
                myinfo_auth.text= User.AUTH_FINISHED
                User.auth = User.AUTH_FINISHED
            } else
                myinfo_auth.text=User.AUTH_UNFINISHED
            User.log()
        }

//        /* 函数：我的信息页面跳转*/
//        intoMyinfo.setOnClickListener(){
//            val intent = Intent(activity, MatchResultActivity::class.java)
//            intent.putExtra("uid", User.id)
//            startActivity(intent)
//        }
       /* 函数：我的寻人信息页面跳转*/
        intoMyfind.setOnClickListener(){
            val intent = Intent(activity, MyFindActivity::class.java)
            intent.putExtra("uid", User.id)
            startActivity(intent)
        }
        /*函数：我的寻亲页面跳转*/
        intoMyrelative.setOnClickListener(){
            val intent = Intent(activity, MyRelativeActivity::class.java)
            intent.putExtra("uid", User.id)
            startActivity(intent)
        }
        /*函数：我的线索页面跳转*/
        intoMyclue.setOnClickListener(){
            val intent = Intent(activity, MyClueActivity::class.java)
            intent.putExtra("uid", User.id)
            startActivity(intent)
        }
        /*函数：登出页面跳转*/
        logout.setOnClickListener(){
            User.logout()
            val activity = context as Activity
            activity.finish()
        }

        return root
    }

    private fun resetName(viewHolder: ViewHolder):String?{
        val editText = EditText(context)
        var msg:String?=null
        val builder = context?.let {
            AlertDialog.Builder(it).setTitle("请输入用户名：").setView(editText)
                .setPositiveButton(
                    "确定"
                ) { dialogInterface, i ->
                    msg = editText.text.toString().trim()
                    Log.d(tag,"msg is $msg")
                    if(isNameValid(msg)){
                        if(updateUserName(User.id,msg?:User.name)){
                            viewHolder.name.text=msg //显示
                            User.name = msg?:User.name //User更新
                            Toast.makeText(context, "修改成功", Toast.LENGTH_LONG).show()
                            User.log()
                        }else{
                            Toast.makeText(context, "上传失败", Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(context,"修改失败",Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("取消"){
                        dialogInterface, i ->
                    Toast.makeText(context,"取消修改",Toast.LENGTH_LONG).show()
                }
        }
        builder?.create()?.show()
        return msg
    }

    private fun initMyinfo(viewHolder:ViewHolder){
        if(User.image==R.drawable.eg_girl||User.image==R.drawable.eg_boy)
            viewHolder.image.setImageResource(User.image)//显示
        else{
            var getImage = ""
            val t = Thread {
                getImage = Api.get_image(User.image)
            }
            t.start()
            t.join()
            viewHolder.image.setImageBitmap(Util.load(getImage))
        }
        viewHolder.name.text = User.name
        viewHolder.auth.text = User.auth
    }

    private fun isAuthFinished(uid:Int):Boolean{
        return if(User.auth==User.AUTH_UNFINISHED){
            if(authentification(uid)){
                User.auth = User.AUTH_FINISHED
                true
            }else{
                false
            }
        }else true
    }

    private fun authentification(uid:Int):Boolean
    =if(Mode.isLocal()) authentificationLocal(uid)
    else authentificationRemote(uid)

    private fun isNameValid(name:String?):Boolean{
        return name?.length in 3..20
    }

    private fun updateUserName(uid:Int, name: String):Boolean
    =if(Mode.isLocal()) updateUserNameLocal(uid,name)
    else updateUserNameRemote(uid,name)

    private fun uploadImage():Int
    =if(Mode.isLocal()) changeImageLocal()
    else uploadImageRemote()

    private fun updateUserImage(uid:Int, img:Int):Boolean
    =if(Mode.isLocal()) updateUserImageLocal(uid,img)
    else updateUserImageRemote(uid,img)


    private fun uploadImageRemote():Int{

        if (NumStore.updarted==1){
            NumStore.updarted=0
            return NumStore.setImage
        }else{
            // TODO Auto-generated method stub
            val intent = Intent()
            intent.setClass(
                requireActivity(),
                NotePadNewActivity::class.java
            ) //this前面为当前activty名称，class前面为要跳转到得activity名称

            startActivity(intent)

            return R.drawable.eg_girl
        }
    }

}