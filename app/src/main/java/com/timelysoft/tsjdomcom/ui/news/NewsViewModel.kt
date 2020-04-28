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

    fun newsDetail(id: Int): LiveData<NewsDetailModel> {
        val data = MutableLiveData<NewsDetailModel>()
        RetrofitService.apiService().newsDetail(id).enqueue(object : Callback<NewsDetailModel> {
            override fun onFailure(call: Call<NewsDetailModel>, t: Throwable) {
                println()
            }

            override fun onResponse(
                call: Call<NewsDetailModel>, response: Response<NewsDetailModel>
            ) {
                data.value = response.body()
            }
        })
        return data
    }

    fun newsComment(id: Int): LiveData<List<NewsCommentsModel>> {
        val data = MutableLiveData<List<NewsCommentsModel>>()

        RetrofitService.apiService().newsComment(id)
            .enqueue(object : Callback<List<NewsCommentsModel>> {
                override fun onFailure(call: Call<List<NewsCommentsModel>>, t: Throwable) {
                    println()
                }

                override fun onResponse(
                    call: Call<List<NewsCommentsModel>>,
                    response: Response<List<NewsCommentsModel>>
                ) {
                    data.value = response.body()
                }
            })
        return data
    }

    fun newsCommentPost(body: NewsCommentRequest): LiveData<Boolean> {
        val data = MutableLiveData<Boolean>()
        RetrofitService.apiService().newsCommentPost(body)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    data.value = false
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    data.value = response.isSuccessful
                }

            })

        return data
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