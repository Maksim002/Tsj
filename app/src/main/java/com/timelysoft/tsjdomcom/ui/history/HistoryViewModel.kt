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

    fun addresses(): LiveData<ResultStatus<List<AddressModel>>>{
        return  repository.addresses()
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

    fun download(id: Int): LiveData<ResultStatus<String>>{
        return repository.downloadLink(id)
    }
}