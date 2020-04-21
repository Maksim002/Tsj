package com.timelysoft.tsjdomcom.ui.reference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.*
import com.timelysoft.tsjdomcom.service.request.CertificateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReferenceViewModel : ViewModel() {
    fun references(id: Int): LiveData<List<ReferenceLiteModel>> {
        val data = MutableLiveData<List<ReferenceLiteModel>>()
        RetrofitService.apiService().references(id)
            .enqueue(object : Callback<List<ReferenceLiteModel>> {
                override fun onFailure(call: Call<List<ReferenceLiteModel>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<ReferenceLiteModel>>,
                    response: Response<List<ReferenceLiteModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

            })


        return data
    }

    fun reference(id: Int): LiveData<ReferencesFullModel> {
        val data = MutableLiveData<ReferencesFullModel>()
        RetrofitService.apiService().reference(id).enqueue(object : Callback<ReferencesFullModel> {
            override fun onFailure(call: Call<ReferencesFullModel>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<ReferencesFullModel>,
                response: Response<ReferencesFullModel>
            ) {
                if (response.isSuccessful)
                    data.value = response.body()
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
            }
        })

        return data
    }


    fun relatives(): LiveData<List<MessagesPersonsModel>> {

        val data = MutableLiveData<List<MessagesPersonsModel>>()
        RetrofitService.apiService().relatives()
            .enqueue(object : Callback<List<MessagesPersonsModel>> {
                override fun onFailure(call: Call<List<MessagesPersonsModel>>, t: Throwable) {

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

    fun updateReference(item: CertificateRequest): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().updateReferences(item).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                data.value = response.isSuccessful
            }

        })
        return data
    }


    fun managers(id: Int): LiveData<List<ManagerResponse>> {
        val data = MutableLiveData<List<ManagerResponse>>()
        RetrofitService.apiService().managers(id).enqueue(object : Callback<List<ManagerResponse>> {
            override fun onFailure(call: Call<List<ManagerResponse>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<ManagerResponse>>, response: Response<List<ManagerResponse>>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

    fun chooseManager(helpId: Int, chairmanId: Int): LiveData<String> {
        val data = MutableLiveData<String>()
        RetrofitService.apiService().downloadCertificate(helpId, chairmanId)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    data.value = ""
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }else{
                        data.value = ""
                    }
                }

            })
        return data
    }
}