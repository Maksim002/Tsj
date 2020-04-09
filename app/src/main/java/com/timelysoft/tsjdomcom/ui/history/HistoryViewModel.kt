package com.timelysoft.tsjdomcom.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {
    fun addresses(): LiveData<List<AddressModel>> {
        val data = MutableLiveData<List<AddressModel>>()

        RetrofitService.apiService().addresses().enqueue(object : Callback<List<AddressModel>> {
            override fun onResponse(call: Call<List<AddressModel>>, response: Response<List<AddressModel>>){
                if (response.isSuccessful){
                    data.value = response.body()
                }
            }
            override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
            }
        })

        return data
    }

    fun operations(): LiveData<List<OperationsModel>> {
        val data = MutableLiveData<List<OperationsModel>>()

        RetrofitService.apiService().operations().enqueue(object : Callback<List<OperationsModel>> {
            override fun onFailure(call: Call<List<OperationsModel>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<OperationsModel>>,
                response: Response<List<OperationsModel>>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

        })

        return data
    }

    fun periods(): LiveData<PeriodsModel> {
        val data = MutableLiveData<PeriodsModel>()

        RetrofitService.apiService().periods().enqueue(object : Callback<PeriodsModel> {
            override fun onFailure(call: Call<PeriodsModel>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<PeriodsModel>,
                response: Response<PeriodsModel>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }

        })

        return data
    }

    fun  services(id: Int): LiveData<List<ServicesModel>> {
        val data = MutableLiveData<List<ServicesModel>>()

        RetrofitService.apiService().services(id).enqueue(object : Callback<List<ServicesModel>> {
            override fun onFailure(call: Call<List<ServicesModel>>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<List<ServicesModel>>,
                response: Response<List<ServicesModel>>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                }
            }
        })
        return data
    }

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