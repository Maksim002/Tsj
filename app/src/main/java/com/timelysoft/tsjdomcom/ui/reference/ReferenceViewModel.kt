package com.timelysoft.tsjdomcom.ui.reference

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.*
import com.timelysoft.tsjdomcom.service.request.CertificateRequest

class ReferenceViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun references(id: Int): LiveData<ResultStatus<List<ReferenceLiteModel>>>{
        return repository.references(id)
    }

    fun reference(id: Int): LiveData<ResultStatus<ReferencesFullModel>>{
        return repository.reference(id)
    }

    fun addresses(): LiveData<ResultStatus<List<AddressModel>>>{
        return  repository.addresses()
    }

    fun relatives(): LiveData<ResultStatus<List<MessagesPersonsModel>>>{
        return repository.relatives()
    }

    fun addReferences(item: CertificateRequest): LiveData<ResultStatus<Nothing>>{
        return repository.addReferences(item)
    }

    fun updateReference(item: CertificateRequest): LiveData<ResultStatus<Nothing>>{
        return repository.updateReferences(item)
    }

    fun managers(id: Int): LiveData<ResultStatus<List<ManagerResponse>>>{
        return repository.managers(id)
    }

    fun chooseManager(helpId: Int, chairmanId: Int): LiveData<ResultStatus<String>>{
        return repository.downloadCertificate(helpId, chairmanId)
    }
}