package com.example.tsj.ui.login.forgot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.ui.login.LoginViewModel
import com.example.tsj.ui.message.MessagesViewModel
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

class ForgotActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        initViews()
    }




    private fun initViews() {
        back_button.setOnClickListener {
            onBackPressed()
        }

        forgot_send_button.setOnClickListener {
            if (validateEmailForgot(text_email_forgot.text.toString())) {
                forgot_container_email_input.isErrorEnabled = false
                sendEmail()
            } else {
                forgot_container_email_input.error = "Логин введен не правильно"
            }
        }
    }

    //проверка email
    private fun validateEmailForgot(email: String): Boolean {
        val regExpn =
            ("""^(([\w-]+\.)+[\w-]+|([a-zA-Z]{1}|[\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\w-]+\.)+[a-zA-Z]{2,4})$""")
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    private fun sendEmail() {

        val email = text_email_forgot.text.toString()
        MainActivity.alert.show()
        viewModel.forgotPassword(email).observe(this, Observer {
            if (it){
                Toast.makeText(applicationContext, "На ваш email отправлено пиьсмо!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Такого email не существует", Toast.LENGTH_SHORT).show()
            }
            MainActivity.alert.hide()
        })

    }
}