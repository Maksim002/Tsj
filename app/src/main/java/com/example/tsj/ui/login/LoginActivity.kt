package com.example.tsj.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.renderscript.Short4
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.tsj.MainActivity
import com.example.tsj.R
import com.example.tsj.service.AppPreferences
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AuthModel
import com.example.tsj.ui.contact.ContactFragment
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class LoginActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        AppPreferences.init(application)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

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

        if (AppPreferences.isLogined) {
            startActivity(Intent(this, MainActivity::class.java))

            val e = AppPreferences.preferences.edit()
            e.putBoolean("TsjDom", true)
            e.apply()
        }

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
