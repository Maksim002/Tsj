package com.example.tsj.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.model.MessageModel
import com.example.tsj.service.RetrofitService
import retrofit2.Call
import retrofit2.Response

class MessagesViewModel : ViewModel() {

    fun messages(
        id : Int
    ): LiveData<List<MessageModel>> {
        val data = MutableLiveData<List<MessageModel>>()


        RetrofitService.apiService().messages(id)
            .enqueue(object : retrofit2.Callback<List<MessageModel>> {
                override fun onFailure(call: Call<List<MessageModel>>, t: Throwable) {
                    println("failure")
                }

                override fun onResponse(
                    call: Call<List<MessageModel>>,
                    response: Response<List<MessageModel>>
                ) {
                    data.value = response.body()
                }
            })
        return data
    }

}