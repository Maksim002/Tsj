package com.timelysoft.tsjdomcom.ui.login.forgot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.timelysoft.tsjdomcom.MainActivity
import com.timelysoft.tsjdomcom.R
import com.timelysoft.tsjdomcom.service.Status
import com.timelysoft.tsjdomcom.ui.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_forgot.*
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

        viewModel.forgotPassword(email).observe(this, Observer { result ->
            val msg = result.msg
            MainActivity.alert.hide()
            when(result.status){
                Status.SUCCESS ->{
                    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                }
                Status.ERROR, Status.NETWORK ->{
                    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}