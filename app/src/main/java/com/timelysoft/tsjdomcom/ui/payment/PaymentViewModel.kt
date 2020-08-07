package com.timelysoft.tsjdomcom.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.payment.InvoicesIssuedModel
import com.timelysoft.tsjdomcom.service.model.payment.PaymentDefaultPeriodModel
import okhttp3.MultipartBody

class PaymentViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun invoicesIssued(dataFrom: String, dataTo: String): LiveData<ResultStatus<ArrayList<InvoicesIssuedModel>>> {
        return repository.invoicesIssued(dataFrom, dataTo)
    }
    fun paymentDefaultPeriod(): LiveData<ResultStatus<PaymentDefaultPeriodModel>> {
        return repository.paymentDefaultPeriod()
    }

    fun paymentDownloadTemplate(): LiveData<ResultStatus<String>> {
        return repository.paymentDownloadTemplate()
    }

    fun paymentDownloadSave(file: ArrayList<MultipartBody.Part>): LiveData<ResultStatus<Nothing>> {
        return repository.paymentDownloadSave(file)
    }
}