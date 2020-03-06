package com.example.tsj.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .addHeader("Authorization", "bearer " + AppPreferences.token)
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val client =
        OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()


    private fun retrofit(baseUrl: String = "http://167.114.201.175:204/api/"): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    fun apiServise(): ApiService {
        return retrofit().create(ApiService::class.java)
    }


    private fun retrofit2(baseUrl: String = "http://192.168.1.199:206/api/"): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()


    fun apiServise2(): ApiService {
        return retrofit2().create(ApiService::class.java)
    }
}