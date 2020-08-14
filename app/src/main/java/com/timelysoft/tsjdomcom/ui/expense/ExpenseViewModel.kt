package com.timelysoft.tsjdomcom.ui.expense

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.expense.*

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

    fun comingDocument(id: Int): LiveData<ResultStatus<ComingDocumentModel>> {
        return repository.comingDocument(id)
    }

    fun changeListType(): LiveData<ResultStatus<ArrayList<ChangeListType>>> {
        return repository.changeListType()
    }

    fun changeListManagers(): LiveData<ResultStatus<ArrayList<ChangeListManagers>>> {
        return repository.changeListManagers()
    }

    fun userChangeEdit(body: ChangeEditModel): LiveData<ResultStatus<Nothing>> {
        return repository.userChangeEdit(body)
    }
}