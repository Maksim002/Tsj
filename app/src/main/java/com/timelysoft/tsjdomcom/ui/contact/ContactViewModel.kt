package com.timelysoft.tsjdomcom.ui.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.AddressModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel : ViewModel() {
    private val repository = NetworkRepository()


    fun addressesN(): LiveData<ResultStatus<List<AddressModel>>>{
        return  repository.addresses()
    }

//    fun addresses(): LiveData<List<AddressModel>> {
//        val data = MutableLiveData<List<AddressModel>>()
//
//        RetrofitService.apiService().addresses().enqueue(object : Callback<List<AddressModel>> {
//            override fun onResponse(
//                call: Call<List<AddressModel>>,
//                response: Response<List<AddressModel>>
//            ) {
//                if (response.isSuccessful) {
//                    data.value = response.body()
//
//                }
//            }
//
//            override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
//                println()
//            }
//        })
//
//        return data
//    }
}