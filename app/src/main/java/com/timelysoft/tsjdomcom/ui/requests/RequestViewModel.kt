package com.timelysoft.tsjdomcom.ui.requests

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.*


import com.timelysoft.tsjdomcom.service.request.AddRequest
import com.timelysoft.tsjdomcom.service.request.UpdateRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun requests(): LiveData<ResultStatus<List<RequestsModel>>>{
        return repository.requests()
    }

    fun requestTypes(): LiveData<ResultStatus<List<RequestTypeModel>>>{
        return repository.requestTypes()
    }

    fun requestAddresses(): LiveData<ResultStatus<List<RequestAddressesModel>>>{
        return repository.requestAddresses()
    }

    fun addRequest(body: AddRequest): LiveData<ResultStatus<Nothing>>{
        return repository.requestAdd(body)
    }

    fun updateRequest(body: UpdateRequest): LiveData<ResultStatus<Unit>>{
        return repository.requestUpdate(body)
    }

    fun deleteRequest(id: Int): LiveData<ResultStatus<Nothing>>{
        return repository.requestDelete(id)
    }

    fun getRequest(id: Int): LiveData<ResultStatus<RequestModel>>{
        return repository.requestGet(id)
    }
}