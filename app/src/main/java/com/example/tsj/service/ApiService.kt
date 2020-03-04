package com.example.tsj.service

import com.example.tsj.service.model.*
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
    fun servicesA(@Path("id") id:Int): Call<List<BalanceStatusModel>>

    @GET("Balance/Operations")
    fun operations(): Call<List<OperationsModel>>

    @GET("Balance/Periods")
    fun periods(): Call<PeriodsModel>

    @GET("Balance/{id}/Services")
    fun servicesB(@Path("id") id: Int): Call<List<ServicesModel>>

}