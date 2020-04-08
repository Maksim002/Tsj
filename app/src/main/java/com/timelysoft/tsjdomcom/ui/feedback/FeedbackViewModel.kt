package com.timelysoft.tsjdomcom.ui.feedback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.ChangePasswordModel
import com.timelysoft.tsjdomcom.service.request.FeedbackRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedbackViewModel : ViewModel() {

    fun sendFeedback(body: FeedbackRequest): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().sendFeedback(body).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.isSuccessful
            }
        })
        return data
    }

}