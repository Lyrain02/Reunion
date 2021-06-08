package com.example.mytest.ui.square

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class ViewPagerAdapter(
    fm: FragmentManager?,
    private val mlist: List<Fragment>
) :
    FragmentPagerAdapter(fm!!) {

    private val mfragmentManager: FragmentManager? = null
    override fun getItem(position: Int): Fragment {
        return mlist[position]
    }

    override fun getCount(): Int {
        return mlist.size
    }

}

