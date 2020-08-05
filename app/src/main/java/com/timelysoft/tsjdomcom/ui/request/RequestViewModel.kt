package com.timelysoft.tsjdomcom.ui.request

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.provider.ProviderInvoices
import com.timelysoft.tsjdomcom.service.model.request.*

class RequestViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun listUser(dataFrom: String, dataTo: String, typeId: Int?): LiveData<ResultStatus<ArrayList<UserRequestModel>>> {
        return repository.listUser(dataFrom, dataTo, typeId)
    }

    fun listUserType(): LiveData<ResultStatus<ArrayList<UserRequestTypeModel>>> {
        return repository.listUserType()
    }

    fun userRequestSave(dataFrom: String, dataTo: String, typeId: Int?): LiveData<ResultStatus<String>> {
        return repository.userRequestSave(dataFrom, dataTo, typeId)
    }

    fun listUserView(vieWId: Int): LiveData<ResultStatus<ListUserView>> {
        return repository.listUserView(vieWId)
    }

    fun userRequestEdit(body: UserRequestEdit): LiveData<ResultStatus<Nothing>> {
        return repository.userRequestEdit(body)
    }

    fun listUserStatus(): LiveData<ResultStatus<ArrayList<ListUserStatus>>> {
        return repository.listUserStatus()
    }

    fun linkSupplier(id: Int, pid: Int): LiveData<ResultStatus<Nothing>> {
        return repository.linkSupplier(id, pid)
    }

    fun userRequestProvider(): LiveData<ResultStatus<ArrayList<ProviderInvoices>>> {
        return repository.userRequestProvider()
    }
}