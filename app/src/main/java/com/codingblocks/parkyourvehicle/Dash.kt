package com.codingblocks.parkyourvehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_dash.*

class Dash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        val sessionManager = SessionManager(this)
        val userdetails = sessionManager.getUserDetailFromSession()
        val dashboard_username = userdetails.get(sessionManager.key_name)
        dash_name.text = "Hey, ${dashboard_username.toString().capitalize()}"

    }
    fun add_btn_activity(view: View) {


        val intent  = Intent (this,add_vehicle::class.java)
        startActivity(intent)




    }

    fun employee_list(view: View) {}
    fun logoutbtn(view: View) {}
    fun Profile(view: View) {}
}