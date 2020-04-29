package com.timelysoft.tsjdomcom.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.AddressModel
import com.timelysoft.tsjdomcom.service.model.BalanceStatusModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BalanceViewModel : ViewModel() {

    private val repository = NetworkRepository()

    fun addresses(): LiveData<ResultStatus<List<AddressModel>>>{
        return  repository.addresses()
    }

    fun services(placementId: Int): LiveData<ResultStatus<List<BalanceStatusModel>>>{
        return repository.status(placementId)
    }
}