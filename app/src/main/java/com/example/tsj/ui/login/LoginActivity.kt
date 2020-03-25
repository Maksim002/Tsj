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
import com.example.tsj.ui.login.forgot.ForgotActivity
import com.example.tsj.utils.MyUtils
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val transition = try {
            intent.extras!!.getBoolean("transition")
        } catch (e: Exception) {
            false
        }

        if (AppPreferences.started && !transition) {
            startMainActivity()
        }

        AppPreferences.started = true
        login_skip.setOnClickListener {
            AppPreferences.isLogined = false
            startMainActivity()
        }

        forgotPassword()
    }

    private fun forgotPassword() {

        main_forgot_textview.setOnClickListener {
            val forgotPassword = Intent(this, ForgotActivity::class.java)
            startActivity(forgotPassword)
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

    private fun validate(): Boolean {
        var valid = true
        if (text_email.text.toString().isEmpty() || !MyUtils.emailValidate(text_email.text.toString())) {
            main_container_email_input.error = "Введите правильный адрес"
            valid = false
        } else {
            main_container_email_input.isErrorEnabled = false
        }

        if (text_pass.text.toString().isEmpty()) {
            main_container_password_input.error = "Введите ваш пароль"
            valid = false
        } else {
            main_container_password_input.isErrorEnabled = false
        }

        return valid
    }

    override fun onStart() {
        super.onStart()

        main_enter_button.setOnClickListener {
            if (validate()) {
                val map = HashMap<String, String>()
                map.put("grant_type", "password")
                map.put("username", text_email.text.toString())
                map.put("password", text_pass.text.toString())
                map.put("refresh_token", "")

                viewModel.auth(map).observe(this, Observer {
                    if (it) {
                        startMainActivity()
                        AppPreferences.email = text_email.text.toString()
                    } else {
                        Toast.makeText(this, "Не правильный логин или пароль", Toast.LENGTH_LONG)
                            .show()
                    }

                })
            }
        }
    }

    private fun startMainActivity() {
        val loged = Intent(this, MainActivity::class.java)
        loged.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loged)
    }


    //проверка пароля
    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }


}

