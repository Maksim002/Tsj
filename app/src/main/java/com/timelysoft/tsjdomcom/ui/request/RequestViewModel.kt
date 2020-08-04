package com.timelysoft.tsjdomcom.ui.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.request.UserRequestModel
import com.timelysoft.tsjdomcom.service.model.request.UserRequestTypeModel

class RequestViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun listUser(dataFrom: String, dataTo: String, typeId: Int): LiveData<ResultStatus<ArrayList<UserRequestModel>>> {
        return repository.listUser(dataFrom, dataTo, typeId)
    }

    fun listUserType(): LiveData<ResultStatus<ArrayList<UserRequestTypeModel>>> {
        return repository.listUserType()
    }

    fun userRequestSave(dataFrom: String, dataTo: String, typeId: Int): LiveData<ResultStatus<String>> {
        return repository.userRequestSave(dataFrom, dataTo, typeId)
    }
}