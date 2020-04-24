package com.timelysoft.tsjdomcom.ui.message

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.model.MessageItemModel
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class   MessagesViewModel : ViewModel() {

    private val repository = NetworkRepository()

    fun messages(id: Int): LiveData<ResultStatus<List<MessageItemModel>>>{
        return repository.messages(id)
    }

    fun message(idMessage: Int): LiveData<ResultStatus<MessageModel>>{
        return repository.message(idMessage)
    }

    fun deleteMessage(idMessage: Int): LiveData<ResultStatus<Nothing>> {
        return repository.deleteMessage(idMessage)
    }

    fun sendMessageToManager(
        body: String,
        title: String,
        file: ArrayList<MultipartBody.Part>
    ): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        if (file.isEmpty()) file.add(addEmptyFile())
        RetrofitService.apiService().sendMessageToManager(body, title, file)
            .enqueue(object : Callback<Unit> {
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
            .enqueue(object : Callback<List<MessagesHousesModel>> {
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
            .enqueue(object : Callback<List<MessagesPlacementsModel>> {
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
            .enqueue(object : Callback<List<MessagesPersonsModel>> {
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

    fun messageToPerson(
        id: Int,
        body: String,
        title: String,
        file: ArrayList<MultipartBody.Part>
    ): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        if (file.isEmpty()) file.add(addEmptyFile())
        RetrofitService.apiService().messageToPerson(id, body, title, file)
            .enqueue(object : Callback<Unit> {
                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    data.value = false
                }

                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    data.value = response.isSuccessful
                }

            })

        return data
    }

    fun messageTypes(): LiveData<List<MessagesPersonsModel>> {
        val data = MutableLiveData<List<MessagesPersonsModel>>()
        RetrofitService.apiService().messageTypes()
            .enqueue(object : Callback<List<MessagesPersonsModel>> {
                override fun onFailure(call: Call<List<MessagesPersonsModel>>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<List<MessagesPersonsModel>>,
                    response: Response<List<MessagesPersonsModel>>
                ) {
                    if (response.isSuccessful) data.value = response.body()

                }

            })
        return data
    }

    private fun addEmptyFile(): MultipartBody.Part {
        val empty: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
        return MultipartBody.Part.createFormData("empty", "", empty)
    }

    fun reply(idMessage: Int): LiveData<ReplyModel> {
        val data = MutableLiveData<ReplyModel>()

        RetrofitService.apiService().reply(idMessage).enqueue(object : Callback<ReplyModel> {
                override fun onFailure(call: Call<ReplyModel>, t: Throwable) {
                    println("failure")
                }

                override fun onResponse(call: Call<ReplyModel>, response: Response<ReplyModel>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }
            })
        return data
    }
}