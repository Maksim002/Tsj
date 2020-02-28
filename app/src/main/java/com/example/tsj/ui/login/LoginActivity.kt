package com.example.tsj.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*


class LoginActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppPreferences.init(application)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        if (AppPreferences.started) {
            startActivity(Intent(this, MainActivity::class.java))
        }
        AppPreferences.started = true

        login_skip.setOnClickListener {
            AppPreferences.isLogined = false
            startActivity(
                Intent(this, MainActivity::class.java)
            )
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        //Двойной клик чтобы выйти из приложения
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onStart() {
        super.onStart()

        main_enter_button.setOnClickListener {

            var map = HashMap<String, String>()
            map.put("grant_type", "password")
            map.put("username", text_email.text.toString())
            map.put("password", text_password.text.toString())

            viewModel.auth(map).observe(this, Observer { result ->
                if (AppPreferences.isLogined == true){
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                }
                Toast.makeText(application, result.toString(), Toast.LENGTH_LONG).show()
            })
        }
    }
}
