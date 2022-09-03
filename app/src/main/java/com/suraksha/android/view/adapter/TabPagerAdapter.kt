package com.suraksha.android.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val titleList = ArrayList<String>(1)
    val fragmentList = ArrayList<Fragment>(1)
    //private val mData =androidx.collection.ArrayMap<CharSequence, Fragment>()
    fun addFragment(title: CharSequence, fragment: Fragment) {
        titleList.add(title.toString())
        fragmentList.add(fragment)
        //mData[title] = fragment
    }
    fun addTitle(title: CharSequence)
    {
        titleList.add(title.toString())

    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)

    }

    override fun getCount(): Int {
        return fragmentList.size

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList.get(position)
        //return mData.keyAt(position)
    }

}