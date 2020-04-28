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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {
    private val repository = NetworkRepository()

    fun news(): LiveData<ResultStatus<List<NewsModel>>>{
        return repository.news()
    }

    fun newsDetailN(id: Int): LiveData<ResultStatus<NewsDetailModel>>{
        return repository.newsDetail(id)
    }

    fun newsCommentN(id: Int): LiveData<ResultStatus<List<NewsCommentsModel>>>{
        return repository.newsComment(id)
    }

    fun newsCommentPostN(body: NewsCommentRequest): LiveData<ResultStatus<Nothing>>{
        return repository.newsCommentPost(body)
    }

    fun newsCommentDelete(id: Int): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()

        RetrofitService.apiService().newsCommentDelete(id).enqueue(object : Callback<String> {
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