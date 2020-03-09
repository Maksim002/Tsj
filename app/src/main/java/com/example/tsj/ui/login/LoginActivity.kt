package com.example.tsj.ui.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import com.example.tsj.ui.login.forgot.ForgotActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import java.util.regex.Pattern


class LoginActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppPreferences.init(application)

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

    override fun onStart() {
        super.onStart()

        main_enter_button.setOnClickListener {

            var map = HashMap<String, String>()
            map.put("grant_type", "password")
            map.put("username", text_email.text.toString())
            map.put("password", text_password.text.toString())

            viewModel.auth(map).observe(this, Observer { result ->

                if (AppPreferences.isLogined) {
                    startMainActivity()
                }
                Toast.makeText(application, result.toString(), Toast.LENGTH_LONG).show()
            })

            checkEditText()
        }
    }

    private fun startMainActivity() {
        val loged = Intent(this, MainActivity::class.java)
        loged.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loged)
    }

    private fun checkEditText() {

        //проверка пароля
        if (text_password.text!!.isEmpty()) {
            main_container_password_input.error = "Пароль не может быть пустым"
        } else {
            main_container_password_input.error = null
        }

        //проверка email
        val emailText = text_email.text.toString()
        val regExpn = ("""^(([\w-]+\.)+[\w-]+|([a-zA-Z]{1}|[\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\w-]+\.)+[a-zA-Z]{2,4})$""")
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(emailText)
        if (!matcher.matches()) {
            main_container_email_input.error = "Не правильный e-mail"
        } else {
            main_container_email_input.error = null
        }
    }

}

