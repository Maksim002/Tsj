package com.timelysoft.tsjdomcom.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryViewModel : ViewModel() {
    private val repository = NetworkRepository()

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

    fun operations(): LiveData<ResultStatus<List<OperationsModel>>> {
        return repository.operations()
    }

    fun periods(): LiveData<ResultStatus<PeriodsModel>>{
        return repository.periods()
    }

    fun services(id: Int): LiveData<ResultStatus<List<ServicesModel>>>{
        return repository.services(id)
    }

    fun invoices(placementId: Int, serviceId: Int, operationId: Int, dateFrom: String, dateTo: String): LiveData<ResultStatus<CurrentBalance>>{
        return repository.invoices(placementId, serviceId, operationId, dateFrom, dateTo)
    }

    fun downloadN(id: Int): LiveData<ResultStatus<String>>{
        return repository.downloadLink(id)
    }
}