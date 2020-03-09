package com.example.tsj.ui.history.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.OperationsModel
import com.example.tsj.service.model.PeriodsModel
import com.example.tsj.service.model.ServicesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {
    fun addresses(): LiveData<List<AddressModel>> {
        val data = MutableLiveData<List<AddressModel>>()

        RetrofitService.apiServise().addresses().enqueue(object : Callback<List<AddressModel>> {
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

        RetrofitService.apiServise().operations().enqueue(object : Callback<List<OperationsModel>> {
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

        RetrofitService.apiServise().periods().enqueue(object : Callback<PeriodsModel> {
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

        RetrofitService.apiServise().services(id).enqueue(object : Callback<List<ServicesModel>> {
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
}