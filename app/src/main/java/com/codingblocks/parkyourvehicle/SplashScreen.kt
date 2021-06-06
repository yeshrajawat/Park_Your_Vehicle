package com.codingblocks.parkyourvehicle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    lateinit var topAnim:Animation
    lateinit var botAnim :Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContentView(R.layout.activity_splash_screen)

        //Animataions
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        botAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)
        imageview_splash.animation = topAnim

        textView_splash.animation = botAnim


        //Delay
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, Login_Page::class.java)
            startActivity(intent)
            finish()

        }, 3000)
    }
}