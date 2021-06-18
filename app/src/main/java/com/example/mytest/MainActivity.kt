package com.example.mytest

import android.content.Intent
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.multidex.MultiDex
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mytest.ui.myinfo.MyinfoFragment
import com.example.mytest.user.User

class MainActivity : AppCompatActivity() {
    val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        MultiDex.install(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,R.id.navigation_dashboard, R.id.navigation_notifications))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)



        val id = intent.getIntExtra("id",0)
        Log.d(tag,id.toString())
//        if (id == 3) {
//            Log.d("aaaa","intent"+id)
//            getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.navigation_notifications, MyinfoFragment())
//                .addToBackStack(null)
//                .commit()
//        }
        Log.d(tag,"Welcome user ${User.name}, uid is ${User.id}")

    }
}