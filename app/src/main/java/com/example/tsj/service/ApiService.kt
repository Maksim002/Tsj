package com.example.tsj.service

import com.example.tsj.model.MessageModel
import com.example.tsj.service.model.*
import com.example.tsj.service.request.AddRequest
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("Token")
    fun auth(@FieldMap params: Map<String, String>): Call<AuthModel>

    @GET("News")
    fun news(): Call<List<NewsModel>>

    @GET ("Messages")
    fun messages (@Query("typeId") id : Int) : Call <List<MessageModel>>

    @GET("Balance/Addresses")
    fun addresses(): Call<List<AddressModel>>

    @GET("Balance/{id}/Services/Balance")
    fun status(@Path("id") id: Int): Call<List<BalanceStatusModel>>

    @GET("Balance/Operations")
    fun operations(): Call<List<OperationsModel>>

    @GET("Balance/Periods")
    fun periods(): Call<PeriodsModel>

    @GET("Balance/{id}/Services")
    fun services(@Path("id") id: Int): Call<List<ServicesModel>>

    @GET("Balance/CurrentBalance")
    fun invoices(
        @Query("placementId") placementId: Int,
        @Query("serviceId") serviceId: Int,
        @Query("operationId") operationId: Int,
        @Query("dateTo") dateTo: String,
        @Query("dateFrom") dateFrom: String): Call<CurrentBalance>

    @GET("Requests")
    fun requests(): Call<List<RequestsModel>>

    @GET("Requests/Types")
    fun requestTypes(): Call<List<RequestTypeModel>>

    @GET("Requests/Addresses")
    fun requestAddresses(): Call<List<RequestAddressesModel>>

    @POST("Requests")
    fun requestAdd(@Body body:AddRequest):Call<String>

    @DELETE("Requests/{id}")
    fun requestDelete(@Path("id") id:Int):Call<String>

    @GET("Balance/Invoices/{id}/Download")
    fun downloadLink(@Path("id") id: Int): Call<String>
}