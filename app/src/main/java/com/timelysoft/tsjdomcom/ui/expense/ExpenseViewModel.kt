package com.timelysoft.tsjdomcom.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseExpensesReceiptsModel
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTSJModel
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTypeModel

class ExpenseViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun expenseListTSJ(): LiveData<ResultStatus<ArrayList<ExpenseListTSJModel>>> {
        return repository.expenseListTSJ()
    }

    fun expenseListType(): LiveData<ResultStatus<ArrayList<ExpenseListTypeModel>>> {
        return repository.expenseListType()
    }

    fun expenseExpensesReceipts(id: Int): LiveData<ResultStatus<ExpenseExpensesReceiptsModel>> {
        return repository.expenseExpensesReceipts(id)
    }
}