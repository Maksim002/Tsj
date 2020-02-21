package com.example.tsj.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.tsj.R
import com.example.tsj.ui.login.LoginActivity
//dastan
class SplashActivity : AppCompatActivity() {


    private val SPLASH_TIME: Long = 1500
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initSplashScreen()
    }

    private fun initSplashScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, SPLASH_TIME)
    }
}
