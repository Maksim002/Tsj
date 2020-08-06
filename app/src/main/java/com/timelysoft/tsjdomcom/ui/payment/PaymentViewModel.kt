package com.timelysoft.tsjdomcom.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.payment.InvoicesIssuedModel
import com.timelysoft.tsjdomcom.service.model.payment.PaymentDefaultPeriodModel

class PaymentViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun invoicesIssued(dataFrom: String, dataTo: String): LiveData<ResultStatus<ArrayList<InvoicesIssuedModel>>> {
        return repository.invoicesIssued(dataFrom, dataTo)
    }
    fun paymentDefaultPeriod(): LiveData<ResultStatus<PaymentDefaultPeriodModel>> {
        return repository.paymentDefaultPeriod()
    }
}