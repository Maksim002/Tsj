package com.example.tsj.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        RetrofitService.apiService().auth(map).enqueue(object : Callback<AuthModel> {
            override fun onResponse(call: Call<AuthModel>, response: Response<AuthModel>) {
                if (response.isSuccessful) {
                    AppPreferences.token = response.body()?.accessToken
                    AppPreferences.isLogined = true
                    result.value = true
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