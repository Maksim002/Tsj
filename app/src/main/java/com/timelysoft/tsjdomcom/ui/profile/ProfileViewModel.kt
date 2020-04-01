package com.timelysoft.tsjdomcom.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    fun cnahgePassword(model: ChangePasswordModel): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().changePassword(model)
            .enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    data.value = false
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    data.value = response.isSuccessful

                }

            })


        return data
    }
}