package com.timelysoft.tsjdomcom.ui.servise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.service.AssociationServicesModel
import com.timelysoft.tsjdomcom.service.model.service.CreateServiceModel
import com.timelysoft.tsjdomcom.service.model.service.CreateTypeService
import com.timelysoft.tsjdomcom.service.model.service.PeriodServiceModel

class ServiceViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun listService(): LiveData<ResultStatus<AssociationServicesModel>> {
        return repository.listService()
    }

    fun createService(body: CreateServiceModel): LiveData<ResultStatus<Nothing>> {
        return repository.createService(body)
    }

    fun createPeriodService(): LiveData<ResultStatus<ArrayList<PeriodServiceModel>>> {
        return repository.createPeriodService()
    }

    fun createTypeService(): LiveData<ResultStatus<ArrayList<CreateTypeService>>> {
        return repository.createTypeService()
    }
}