package com.timelysoft.tsjdomcom.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.payment.*
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

    fun paymentInformationTsj(): LiveData<ResultStatus<InformationTsjModel>> {
        return repository.paymentInformationTsj()
    }

    fun paymentPaymentReport(from: String, to: String, serviceId: Int?): LiveData<ResultStatus<ArrayList<PaymentReportModel>>> {
        return repository.paymentPaymentReport(from, to, serviceId)
    }

    fun paymentListService(): LiveData<ResultStatus<ArrayList<ListServiceModel>>> {
        return repository.paymentListService()
    }

    fun paymentDateDefault(): LiveData<ResultStatus<DefaultPeriodModel>> {
        return repository.paymentDateDefault()
    }

    fun paymentListPayment(from: String, to: String, serviceId: Int?): LiveData<ResultStatus<ArrayList<PaymentReportModel>>> {
        return repository.paymentListPayment(from, to, serviceId)
    }

    fun paymentSave(from: String, to: String, serviceId: Int?): LiveData<ResultStatus<String>> {
        return repository.paymentSave(from, to, serviceId)
    }
}