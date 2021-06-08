package com.example.mytest.ui.square

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mytest.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy


class SquareFragment : Fragment() {
    //广场页面
    private lateinit var homeViewModel: SquareViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_square, container, false)
        val viewPager: ViewPager2 = root.findViewById(R.id.viewPager)
        val tabLayout: TabLayout = root.findViewById(R.id.tablayout)
        val fragmentList: ArrayList<Fragment> = ArrayList()
        val titles = arrayOf("寻人启示", "我想回家")

        fragmentList.add(FirstFragment())
        fragmentList.add(SecondFragment())

        //val adapter = ViewPagerAdapter(childFragmentManager)

        Log.d(Constraints.TAG, "Tab")

        // tabLayout.setupWithViewPager(viewPager,true)

        Log.d(Constraints.TAG, "Tab2")
        viewPager.setAdapter(object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }

            override fun getItemCount(): Int {
                return fragmentList.size
            }
        })

        val tabLayoutMediator =
            TabLayoutMediator(tabLayout, viewPager,
                TabConfigurationStrategy { tab, position -> tab.setText(titles.get(position)) })
        tabLayoutMediator.attach()



        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d(Constraints.TAG, "onTabSelected:" + tab.text)
                viewPager.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        return root
    }


}
