package com.android.luomeiji_rider.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class Viewpage_Adapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    lateinit var mFragments: ArrayList<Fragment>
    lateinit var mTitle: ArrayList<String>
    public var currentfragment: Fragment? = null

    constructor(fm: FragmentManager, fragments: ArrayList<out Fragment>, title: ArrayList<String>) : this(fm) {
        mFragments = ArrayList<Fragment>(fragments)
        mTitle = title
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitle[position]
    }

    override fun getItem(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getCount(): Int {
        return mFragments.size
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        this.currentfragment = `object` as Fragment
        super.setPrimaryItem(container, position, `object`)
    }
}
