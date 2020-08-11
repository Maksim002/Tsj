package com.timelysoft.tsjdomcom.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.timelysoft.tsjdomcom.service.NetworkRepository
import com.timelysoft.tsjdomcom.service.ResultStatus
import com.timelysoft.tsjdomcom.service.RetrofitService
import com.timelysoft.tsjdomcom.service.model.news.NewsCommentsModel
import com.timelysoft.tsjdomcom.service.model.news.NewsDetailModel
import com.timelysoft.tsjdomcom.service.model.news.NewsModel
import com.timelysoft.tsjdomcom.service.request.NewsCommentRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun news(): LiveData<ResultStatus<List<NewsModel>>>{
        return repository.news()
    }

    fun newsDetail(id: Int): LiveData<ResultStatus<NewsDetailModel>>{
        return repository.newsDetail(id)
    }

    fun newsComment(id: Int): LiveData<ResultStatus<List<NewsCommentsModel>>>{
        return repository.newsComment(id)
    }

    fun newsCommentPost(body: NewsCommentRequest): LiveData<ResultStatus<Nothing>>{
        return repository.newsCommentPost(body)
    }

    fun newsCommentDelete(id: Int): LiveData<ResultStatus<Nothing>>{
        return repository.newsCommentDelete(id)
    }

    fun newsAddMessage(file: ArrayList<MultipartBody.Part>?, content: String, title: String): LiveData<ResultStatus<Nothing>>{
        if (file!!.isEmpty()) file.add(addEmptyFile())
        return repository.newsAddMessage(file, content, title)
    }

    private fun addEmptyFile(): MultipartBody.Part {
        val empty: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), "")
        return MultipartBody.Part.createFormData("empty", "", empty)
    }
}