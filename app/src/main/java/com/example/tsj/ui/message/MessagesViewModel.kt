package com.example.tsj.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.model.MessageItemModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.MessageModel
import com.example.tsj.service.model.MessagesHousesModel
import com.example.tsj.service.model.MessagesPersonsModel
import com.example.tsj.service.model.MessagesPlacementsModel
import okhttp3.MultipartBody
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

    fun sendMessageToManager(body: String, title: String, file: List<MultipartBody.Part>): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().sendMessageToManager(body, title, file).enqueue(object : Callback<Unit> {
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                data.value = response.isSuccessful
            }

        })

        return data
    }

    fun houses(): LiveData<List<MessagesHousesModel>> {
        val data = MutableLiveData<List<MessagesHousesModel>>()

        RetrofitService.apiService().houses()
            .enqueue(object: Callback<List<MessagesHousesModel>> {
                override fun onFailure(call: Call<List<MessagesHousesModel>>, t: Throwable) {
                    println("failure")
                }

                override fun onResponse(
                    call: Call<List<MessagesHousesModel>>,
                    response: Response<List<MessagesHousesModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
            })
        return data
    }

    fun placements(id: Int): LiveData<List<MessagesPlacementsModel>> {
        val data = MutableLiveData<List<MessagesPlacementsModel>>()

        RetrofitService.apiService().placements(id)
            .enqueue(object: Callback<List<MessagesPlacementsModel>> {
                override fun onFailure(call: Call<List<MessagesPlacementsModel>>, t: Throwable) {
                    println("failure")
                }

                override fun onResponse(
                    call: Call<List<MessagesPlacementsModel>>,
                    response: Response<List<MessagesPlacementsModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
            })
        return data
    }

    fun persons(id: Int): LiveData<List<MessagesPersonsModel>> {
        val data = MutableLiveData<List<MessagesPersonsModel>>()

        RetrofitService.apiService().persons(id)
            .enqueue(object: Callback<List<MessagesPersonsModel>> {
                override fun onFailure(call: Call<List<MessagesPersonsModel>>, t: Throwable) {
                    println("failure")
                }

                override fun onResponse(
                    call: Call<List<MessagesPersonsModel>>,
                    response: Response<List<MessagesPersonsModel>>
                ) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
            })
        return data
    }

    fun messageToPerson(id: Int ,body: String, title: String, file: List<MultipartBody.Part>): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().messageToPerson(id,body, title, file).enqueue(object : Callback<Unit> {
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