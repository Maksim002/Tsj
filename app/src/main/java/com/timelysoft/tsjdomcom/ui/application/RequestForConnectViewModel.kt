package com.timelysoft.tsjdomcom.ui.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.RequestForConnectModel

class RequestForConnectViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun requestForConnectionN(body: RequestForConnectModel): LiveData<ResultStatus<Nothing>>{
        return repository.requestForConnect(body)
    }
}