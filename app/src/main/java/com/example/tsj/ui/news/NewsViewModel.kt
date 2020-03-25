package com.example.tsj.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.RequestForConnectModel
import com.example.tsj.service.model.news.NewsCommentsModel
import com.example.tsj.service.model.news.NewsDetailModel
import com.example.tsj.service.model.news.NewsModel
import com.example.tsj.service.request.NewsCommentRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    fun news(): LiveData<List<NewsModel>> {
        val data = MutableLiveData<List<NewsModel>>()

        RetrofitService.apiService().news().enqueue(object : Callback<List<NewsModel>> {
            override fun onResponse(
                call: Call<List<NewsModel>>,
                response: Response<List<NewsModel>>
            ) {
                if (response.isSuccessful)
                    data.value = response.body()
            }

            override fun onFailure(call: Call<List<NewsModel>>, t: Throwable) {

            }
        })


        return data
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