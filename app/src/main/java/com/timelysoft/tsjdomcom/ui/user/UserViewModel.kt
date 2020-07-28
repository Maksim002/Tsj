package com.timelysoft.tsjdomcom.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.user.EditModel
import com.timelysoft.tsjdomcom.service.model.user.UserModel

class UserViewModel : ViewModel(){
    private val repository = NetworkRepository()

    fun user(): LiveData<ResultStatus<UserModel>> {
        return  repository.user()
    }

    fun edit(model: EditModel): LiveData<ResultStatus<Nothing>> {
        return  repository.edit(model)
    }
}