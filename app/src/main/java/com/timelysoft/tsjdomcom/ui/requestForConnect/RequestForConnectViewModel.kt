package com.timelysoft.tsjdomcom.ui.requestForConnect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel
import retrofit2.Call
import retrofit2.Response

class RequestForConnectViewModel : ViewModel() {

    fun requestForConnection(body: RequestForConnectModel): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().requestForConnect(body)
            .enqueue(object : retrofit2.Callback<String> {
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