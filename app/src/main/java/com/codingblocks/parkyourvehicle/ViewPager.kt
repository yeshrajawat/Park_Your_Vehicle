package com.codingblocks.parkyourvehicle

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPager(supportFragmentManager: FragmentManager):FragmentPagerAdapter(supportFragmentManager,
    FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    val fragmentlist = ArrayList<Fragment>()
    val titles = ArrayList<String>()
    override fun getCount(): Int {
        return fragmentlist.size
    }

    fun add(fragment:Fragment,title:String){
        fragmentlist.add(fragment)
        titles.add(title)
    }
    override fun getItem(position: Int): Fragment{
        return fragmentlist[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}