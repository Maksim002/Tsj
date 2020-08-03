package com.timelysoft.tsjdomcom.ui.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.provider.*
import com.timelysoft.tsjdomcom.service.request.provider.CreateSupplier
import com.timelysoft.tsjdomcom.service.request.provider.ProviderEdit
import okhttp3.MultipartBody

class ProviderViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun provider(): LiveData<ResultStatus<ArrayList<ProviderModel>>>{
        return repository.provider()
    }

    fun createSupplier(model: CreateSupplier): LiveData<ResultStatus<Nothing>>{
        return repository.createSupplier(model)
    }

    fun providerDelete(idProvider: Int): LiveData<ResultStatus<Nothing>>{
        return repository.providerDelete(idProvider)
    }

    fun providerEdit(model: ProviderEdit): LiveData<ResultStatus<Nothing>>{
        return repository.providerEdit(model)
    }

    fun providerId(id: Int): LiveData<ResultStatus<ProviderIdModel>>{
        return repository.providerId(id)
    }

    fun supplierAccounts(dataFrom: String, dataTo: String, providerId: Int): LiveData<ResultStatus<ArrayList<SupplierAccountsModel>>>{
        return repository.supplierAccounts(dataFrom, dataTo, providerId)
    }

    fun providerInvoices(): LiveData<ResultStatus<ArrayList<ProviderInvoices>>>{
        return repository.providerInvoices()
    }

    fun supplierAccountsDelete(id: Int): LiveData<ResultStatus<Nothing>>{
        return repository.supplierAccountsDelete(id)
    }

    fun providerInvoicesId(id: Int): LiveData<ResultStatus<ProviderInvoicesIdModel>>{
        return repository.providerInvoicesId(id)
    }

    fun providerInvoicesEdit(id: Int, service: String, providerId: Int, date: String, countersValue: Int, paymentAmount: Int, file: ArrayList<MultipartBody.Part>): LiveData<ResultStatus<Nothing>> {
        return repository.providerInvoicesEdit(id, service, providerId, date, countersValue, paymentAmount, file)
    }

    fun addInvoice(service: String, providerId: Int, date: String, countersValue: String, paymentAmount: String, file: ArrayList<MultipartBody.Part>): LiveData<ResultStatus<Nothing>> {
        return repository.addInvoice(service, providerId, date, countersValue, paymentAmount, file)
    }
}