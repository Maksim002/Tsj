package com.example.tsj.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.MainActivity
import com.example.tsj.service.AppPreferences
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AuthModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.HashMap

class LoginViewModel : ViewModel() {

    fun auth(map: HashMap<String, String>): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        RetrofitService.apiServise().auth(map).enqueue(object : Callback<AuthModel> {
            override fun onResponse(call: Call<AuthModel>, response: Response<AuthModel>) {
                if (response.code() == 200) {
                    result.value = true
                    AppPreferences.token = response.body()?.accessToken
                    AppPreferences.isLogined = true
                } else {
                    result.value = false
                }
            }

            override fun onFailure(call: Call<AuthModel>, t: Throwable) {
                result.value = false
            }
        })
        return result
    }


}