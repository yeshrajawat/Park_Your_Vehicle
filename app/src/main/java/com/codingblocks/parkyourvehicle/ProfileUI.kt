package com.codingblocks.parkyourvehicle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile_u_i.*

class ProfileUI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_u_i)

        val sessionManager = SessionManager(this)
        val userdetails = sessionManager.getUserDetailFromSession()
        profile_name.text = userdetails.get(sessionManager.key_name)
         profile_username.text = userdetails.get(sessionManager.key_username)

         profile_email.text = userdetails.get(sessionManager.key_email)
         profile_password.text = userdetails.get(sessionManager.key_password)
        profile_phone.text = userdetails.get(sessionManager.key_phone)
    }
}