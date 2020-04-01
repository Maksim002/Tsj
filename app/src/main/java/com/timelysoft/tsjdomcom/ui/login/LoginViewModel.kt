package com.timelysoft.tsjdomcom.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.AuthModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun forgotPassword(email : String) : LiveData <Boolean> {
        val data = MutableLiveData <Boolean>()

        RetrofitService.apiService().forgotPassword(email).enqueue(object : Callback< String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.isSuccessful
            }

        })
        return data
    }

}