package com.timelysoft.tsjdomcom.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTSJModel

class ExpenseViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun expenseListTSJ(): LiveData<ResultStatus<ArrayList<ExpenseListTSJModel>>> {
        return repository.expenseListTSJ()
    }
}