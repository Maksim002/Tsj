package com.example.tsj.ui.requestForConnect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.RequestForConnectModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

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