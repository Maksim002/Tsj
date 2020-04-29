package com.timelysoft.tsjdomcom.ui.requestForConnect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel
import retrofit2.Call
import retrofit2.Response

class RequestForConnectViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun requestForConnectionN(body: RequestForConnectModel): LiveData<ResultStatus<Nothing>>{
        return repository.requestForConnect(body)
    }
}