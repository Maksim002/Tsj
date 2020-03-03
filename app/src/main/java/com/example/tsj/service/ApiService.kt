package com.example.tsj.service

import com.example.tsj.service.model.AddressModel
import com.example.tsj.service.model.AuthModel
import com.example.tsj.service.model.BalanceStatusModel
import com.example.tsj.service.model.NewsModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("Token")
    fun auth(@FieldMap params:Map<String, String> ): Call<AuthModel>

    @GET("News")
    fun news(): Call<List<NewsModel>>

    @GET("Balance/Addresses")
    fun addresses(): Call<List<AddressModel>>

    @GET("Balance/{id}/Services/Balance")
    fun services(@Path("id") id:Int): Call<List<BalanceStatusModel>>

}