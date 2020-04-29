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

    fun sendMessageToManager(body: String, title: String, file: ArrayList<MultipartBody.Part>): LiveData<ResultStatus<Nothing>>{
        if (file.isEmpty()) file.add(addEmptyFile())
        return repository.sendMessageToManager(body, title, file)
    }

    fun houses(): LiveData<ResultStatus<List<MessagesHousesModel>>>{
        return repository.houses()
    }

    fun placements(id: Int): LiveData<ResultStatus<List<MessagesPlacementsModel>>>{
        return repository.placements(id)
    }

    fun persons(id: Int): LiveData<ResultStatus<List<MessagesPersonsModel>>>{
        return repository.persons(id)
    }

    fun messageToPerson(id: Int, body: String, title: String, file: ArrayList<MultipartBody.Part>): LiveData<ResultStatus<Nothing>>{
        if (file.isEmpty()) file.add(addEmptyFile())
        return repository.messageToPerson(id, body, title, file)
    }

    fun messageTypes(): LiveData<ResultStatus<List<MessagesPersonsModel>>>{
        return repository.messageTypes()
    }

    private fun addEmptyFile(): MultipartBody.Part {
        val empty: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
        return MultipartBody.Part.createFormData("empty", "", empty)
    }

    fun reply(idMessage: Int): LiveData<ResultStatus<ReplyModel>>{
        return repository.reply(idMessage)
    }
}