package com.example.tsj.service

import com.example.tsj.service.model.Example
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("example")
    suspend fun example(): Response<Example>
}