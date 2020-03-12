package com.example.tsj.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tsj.service.RetrofitService
import com.example.tsj.service.model.NewsModel
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

}