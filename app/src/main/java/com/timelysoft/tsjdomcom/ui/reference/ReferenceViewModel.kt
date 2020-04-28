package com.timelysoft.tsjdomcom.ui.reference

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.*
import com.timelysoft.tsjdomcom.service.request.CertificateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReferenceViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun references(id: Int): LiveData<ResultStatus<List<ReferenceLiteModel>>>{
        return repository.references(id)
    }

    fun reference(id: Int): LiveData<ResultStatus<ReferencesFullModel>>{
        return repository.reference(id)
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

    fun relatives(): LiveData<ResultStatus<List<MessagesPersonsModel>>>{
        return repository.relatives()
    }

    fun addReferences(item: CertificateRequest): LiveData<ResultStatus<Nothing>>{
        return repository.addReferences(item)
    }

    fun updateReference(item: CertificateRequest): LiveData<ResultStatus<Nothing>>{
        return repository.updateReferences(item)
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