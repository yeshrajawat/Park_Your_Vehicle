package com.codingblocks.parkyourvehicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setUpTabs()
    }
    private fun setUpTabs() {
        val adapter = ViewPager(supportFragmentManager)
//       adapter.add(VehicleListFragment(),"Vehicles")
        adapter.add(VehicleListFragment(),"Employee List")
        viewpager.adapter = adapter

        tabs.setupWithViewPager(viewpager)


    }
}