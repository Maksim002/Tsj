package com.timelysoft.tsjdomcom.ui.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import com.timelysoft.tsjdomcom.service.request.FeedbackRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun sendFeedbackN(body: FeedbackRequest): LiveData<ResultStatus<Nothing>>{
        return repository.sendFeedback(body)
    }
}