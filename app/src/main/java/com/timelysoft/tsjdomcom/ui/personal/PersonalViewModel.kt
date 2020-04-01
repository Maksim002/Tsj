package com.timelysoft.tsjdomcom.ui.personal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.CurrentBalance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonalViewModel : ViewModel() {

    fun invoices(
        servicesId: Int,
        operationsId: Int,
        placementId: Int,
        tos: String?,
        froms: String?
    ): LiveData<CurrentBalance> {
        val data = MutableLiveData<CurrentBalance>()
        RetrofitService.apiService().invoices(placementId, servicesId, operationsId, tos!!, froms!!)
            .enqueue(object : Callback<CurrentBalance> {

                override fun onFailure(call: Call<CurrentBalance>, t: Throwable) {
                    println()
                }

                override fun onResponse(
                    call: Call<CurrentBalance>,
                    response: Response<CurrentBalance>
                ) {

                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
            })
        return data
    }

    fun download(id: Int?): LiveData<String> {

        val data = MutableLiveData<String>()

        RetrofitService.apiService().downloadLink(id!!).enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
               if (response.isSuccessful){
                   data.value = response.body()
               }
            }

        })

        return data
    }
}