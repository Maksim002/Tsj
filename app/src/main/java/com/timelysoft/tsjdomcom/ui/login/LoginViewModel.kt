package com.timelysoft.tsjdomcom.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.AppPreferences
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.AuthModel
import com.timelysoft.tsjdomcom.service.request.FirebaseTokenModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val repository = NetworkRepository()

    fun auth(map: HashMap<String, String>): LiveData<ResultStatus<AuthModel>>{
        return repository.auth(map)
    }

    fun save(email: String, token: String){
        AppPreferences.email = email
        AppPreferences.token = token
        AppPreferences.isLogined = true
    }

    fun forgotPassword(email: String): LiveData<ResultStatus<Nothing>>{
        return repository.forgotPassword(email)
    }

    fun sendToken(firebaseTokenModel: FirebaseTokenModel) {
        RetrofitService.apiService().sendFirebaseToken(firebaseTokenModel)
            .enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    println()
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    println()
                }

            })
    }

}