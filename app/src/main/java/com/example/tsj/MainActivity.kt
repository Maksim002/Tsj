package com.example.tsj

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tsj.service.AppPreferences
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AuthModel
import com.example.tsj.ui.login.LoginActivity
import com.example.tsj.utils.LoadingAlert
import retrofit2.Call
import java.util.*
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var alert: LoadingAlert
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        alert = LoadingAlert(this)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news,
                R.id.navigation_message,
                R.id.navigation_contacts
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        updateToken()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun updateToken() {
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (AppPreferences.isLogined) {
                    val map = HashMap<String, String>()
                    map.put("grant_type", "refresh_token")
                    map.put("username", "")
                    map.put("password", "")
                    map.put("refresh_token", AppPreferences.refreshToken.toString())
                    RetrofitService.apiService().refreshToken(map)
                        .enqueue(object : Callback<AuthModel> {
                            override fun onFailure(call: Call<AuthModel>, t: Throwable) {

                            }

                            override fun onResponse(
                                call: Call<AuthModel>,
                                response: Response<AuthModel>
                            ) {
                                if (response.isSuccessful) {
                                    AppPreferences.token = response.body()?.accessToken
                                    AppPreferences.refreshToken = response.body()?.refresh_token
                                } else {
                                    alert.hide()
                                    AppPreferences.clear()
                                    val intent = Intent(
                                        this@MainActivity,
                                        LoginActivity::class.java
                                    ).putExtra("transition", true)
                                    startActivity(intent)

                                }
                            }

                        })
                }

            }
        }, 1, 240000)

    }

}
