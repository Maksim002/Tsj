package com.timelysoft.tsjdomcom.ui.servise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.service.AssociationServicesModel

class ServiceViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun listService(): LiveData<ResultStatus<AssociationServicesModel>> {
        return repository.listService()
    }
}