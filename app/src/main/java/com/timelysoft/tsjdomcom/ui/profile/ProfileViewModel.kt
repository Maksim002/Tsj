package com.timelysoft.tsjdomcom.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun changePasswordN(model: ChangePasswordModel): LiveData<ResultStatus<Nothing>>{
        return repository.changePassword(model)
    }
}