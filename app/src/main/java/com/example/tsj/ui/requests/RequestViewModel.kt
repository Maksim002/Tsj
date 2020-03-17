package com.example.tsj.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.RequestAddressesModel
import com.example.tsj.service.model.RequestModel
import com.example.tsj.service.model.RequestTypeModel
import com.example.tsj.service.model.RequestsModel
import com.example.tsj.service.request.AddRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestViewModel : ViewModel() {

    fun requests(): LiveData<List<RequestsModel>> {
        val data = MutableLiveData<List<RequestsModel>>()
        RetrofitService.apiService().requests().enqueue(object : Callback<List<RequestsModel>> {

            override fun onResponse(
                call: Call<List<RequestsModel>>,
                response: Response<List<RequestsModel>>
            ) {
                if (response.isSuccessful)
                    data.value = response.body()
            }

            override fun onFailure(call: Call<List<RequestsModel>>, t: Throwable) {
            }
        })
        return data
    }


    fun requestTypes(): LiveData<List<RequestTypeModel>> {
        val data = MutableLiveData<List<RequestTypeModel>>()
        RetrofitService.apiService().requestTypes()
            .enqueue(object : Callback<List<RequestTypeModel>> {

                override fun onResponse(
                    call: Call<List<RequestTypeModel>>,
                    response: Response<List<RequestTypeModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<List<RequestTypeModel>>, t: Throwable) {

                }


            })


        return data
    }


    fun requestAddresses(): LiveData<List<RequestAddressesModel>> {
        val data = MutableLiveData<List<RequestAddressesModel>>()
        RetrofitService.apiService().requestAddresses()
            .enqueue(object : Callback<List<RequestAddressesModel>> {

                override fun onResponse(
                    call: Call<List<RequestAddressesModel>>,
                    response: Response<List<RequestAddressesModel>>
                ) {
                    data.value = response.body()
                }

                override fun onFailure(call: Call<List<RequestAddressesModel>>, t: Throwable) {

                }


            })

        return data

    }

    fun addRequest(body: AddRequest): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().requestAdd(body).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {

            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.isSuccessful
            }

        })

        return data
    }

    fun deleteRequest(id: Int): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().requestDelete(id).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.code() == 204
            }

        })
        return data
    }

    fun getRequest(id: Int): LiveData<RequestModel> {
        val data = MutableLiveData<RequestModel>()
        RetrofitService.apiService().requestGet(id).enqueue(object : Callback<RequestModel> {
            override fun onFailure(call: Call<RequestModel>, t: Throwable) {
                println()
            }

            override fun onResponse(call: Call<RequestModel>, response: Response<RequestModel>) {
                if (response.isSuccessful){
                    data.value = response.body()
                }
            }

        })


        return data
    }
}