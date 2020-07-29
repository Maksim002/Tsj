package com.timelysoft.tsjdomcom.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.user.EditIdModel
import com.timelysoft.tsjdomcom.service.model.user.UserModel
import com.timelysoft.tsjdomcom.service.request.user.Edit

class UserViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun user(): LiveData<ResultStatus<UserModel>> {
        return  repository.user()
    }

    fun edit(model: Edit): LiveData<ResultStatus<Nothing>> {
        return  repository.edit(model)
    }

    fun editId(id: Int): LiveData<ResultStatus<EditIdModel>> {
        return  repository.editId(id)
    }
}