package com.timelysoft.tsjdomcom.ui.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.counter.DebtsInformationTsjModel
import com.timelysoft.tsjdomcom.service.model.counter.DebtsModel

class CounterViewModel :  ViewModel(){
    private val repository = NetworkRepository()

    fun counterListDebts(): LiveData<ResultStatus<DebtsModel>> {
        return repository.counterListDebts()
    }

    fun debtsInformationTsj(): LiveData<ResultStatus<DebtsInformationTsjModel>> {
        return repository.debtsInformationTsj()
    }

    fun debtsSaveExcel(): LiveData<ResultStatus<String>> {
        return repository.debtsSaveExcel()
    }
}