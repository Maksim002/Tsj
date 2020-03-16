package com.example.tsj.ui.reference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.service.model.ReferenceModel
import com.example.tsj.service.request.CertificateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReferenceViewModel : ViewModel() {
    fun references(id: Int): LiveData<List<ReferenceModel>> {
        val data = MutableLiveData<List<ReferenceModel>>()
        RetrofitService.apiService().references(id)
            .enqueue(object : Callback<List<ReferenceModel>> {
                override fun onFailure(call: Call<List<ReferenceModel>>, t: Throwable) {
                    println()
                }

                override fun onResponse(
                    call: Call<List<ReferenceModel>>,
                    response: Response<List<ReferenceModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

            })


        return data
    }


    fun addresses(): LiveData<List<AddressModel>> {
        val data = MutableLiveData<List<AddressModel>>()

        RetrofitService.apiService().addresses().enqueue(object : Callback<List<AddressModel>> {
            override fun onResponse(
                call: Call<List<AddressModel>>,
                response: Response<List<AddressModel>>
            ) {
                if (response.isSuccessful) {
                    data.value = response.body()

                }
            }

            override fun onFailure(call: Call<List<AddressModel>>, t: Throwable) {
                println()
            }
        })

        return data
    }


    fun relatives(): LiveData<List<MessagesPersonsModel>> {

        val data = MutableLiveData<List<MessagesPersonsModel>>()
        RetrofitService.apiService().relatives()
            .enqueue(object : Callback<List<MessagesPersonsModel>> {
                override fun onFailure(call: Call<List<MessagesPersonsModel>>, t: Throwable) {
                    println()
                }

                override fun onResponse(
                    call: Call<List<MessagesPersonsModel>>,
                    response: Response<List<MessagesPersonsModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

            })


        return data
    }


    fun addReferences(item: CertificateRequest): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().addReferences(item).enqueue(object : Callback<Unit> {
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