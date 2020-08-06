package com.timelysoft.tsjdomcom.ui.servise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.service.Association
import com.timelysoft.tsjdomcom.service.model.service.AssociationServiceModel
import com.timelysoft.tsjdomcom.service.model.service.Service

class ServiceViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun listService(): LiveData<ResultStatus<AssociationServiceModel>> {
        return repository.listService()
    }
}