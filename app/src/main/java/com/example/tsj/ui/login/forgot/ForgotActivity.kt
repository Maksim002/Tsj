package com.example.tsj.ui.login.forgot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.tsj.R
import kotlinx.android.synthetic.main.activity_forgot.*
import java.util.regex.Pattern

class ForgotActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        initViews()
        checkEditText()


    }

    private fun initViews() {
        back_button.setOnClickListener {
            onBackPressed()
        }
    }

    private fun checkEditText() {
        forgot_send_button.setOnClickListener{
            val emailText = text_email_forgot.text.toString()
            val regExpn = ("""^(([\w-]+\.)+[\w-]+|([a-zA-Z]{1}|[\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\w-]+\.)+[a-zA-Z]{2,4})$""")
            val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(emailText)
            if (!matcher.matches()) {
                forgot_container_email_input.error = "Не правильный e-mail"
            } else {
                forgot_container_email_input.error = null
            }
        }
    }
}