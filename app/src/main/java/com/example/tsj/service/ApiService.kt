package com.example.tsj.service

import com.example.tsj.service.model.AuthModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("Token")
    fun auth(@FieldMap params:Map<String, String> ): Call<AuthModel>
}