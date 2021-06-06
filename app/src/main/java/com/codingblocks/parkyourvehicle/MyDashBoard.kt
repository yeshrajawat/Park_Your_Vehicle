package com.codingblocks.parkyourvehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_dash.*
import kotlinx.android.synthetic.main.activity_my_dash_board.*

class MyDashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_dash_board)
        //Session Manager so that this activity can remember the User's Information
        val sessionManager = SessionManager(this)
        val userdetails = sessionManager.getUserDetailFromSession()
        val dashboard_name = userdetails.get(sessionManager.key_name)
        dashboard_text.text = dashboard_name?.capitalize()+"'s DashBoard"





    }

    fun staffmembers(view: View) {
        val intent = Intent(this,Empl::class.java)
        startActivity(intent)

    }

    fun add_vehicle_activity(view: View) {

        val intent = Intent(this,add_vehicle::class.java)
        startActivity(intent)

    }

    fun list_of_vehicles(view: View) {
        val intent = Intent(this,vehicle_list::class.java)
        startActivity(intent)

    }

    fun profilebtn(view: View) {
        val intent = Intent(this,ProfileUI::class.java)
        startActivity(intent)
    }

    fun request_btn(view: View) {
        val intent = Intent(this,User_Requests::class.java)
        startActivity(intent)
    }
}