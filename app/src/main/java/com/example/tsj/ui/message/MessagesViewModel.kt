package com.example.tsj.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.model.MessageItemModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.MessageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MessagesViewModel : ViewModel() {

    fun messages(
        id: Int
    ): LiveData<List<MessageItemModel>> {
        val data = MutableLiveData<List<MessageItemModel>>()


        RetrofitService.apiService().messages(id)
            .enqueue(object :   Callback<List<MessageItemModel>> {
                override fun onFailure(call: Call<List<MessageItemModel>>, t: Throwable) {
                    println("failure")
                }

                override fun onResponse(
                    call: Call<List<MessageItemModel>>,
                    response: Response<List<MessageItemModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
            })
        return data
    }

    fun message(idMessage: Int): LiveData<MessageModel> {
        val data = MutableLiveData<MessageModel>()
        RetrofitService.apiService().message(idMessage).enqueue(object : Callback<MessageModel> {
            override fun onFailure(call: Call<MessageModel>, t: Throwable) {
                println()
            }

            override fun onResponse(call: Call<MessageModel>, response: Response<MessageModel>) {
                data.value = response.body()
            }

        })

        return data
    }

    fun deleteMessage(idMessage: Int): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().deleteMessage(idMessage).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                data.value = response.isSuccessful
            }

        })

        return data
    }

}