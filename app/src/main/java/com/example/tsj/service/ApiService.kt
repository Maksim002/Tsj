package com.example.tsj.service

import com.example.tsj.model.MessageItemModel
import com.example.tsj.model.VoteModel
import com.example.tsj.service.model.*
import com.example.tsj.service.request.AddRequest
import com.example.tsj.service.request.CertificateRequest
import com.example.tsj.service.request.UpdateRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("Token")
    fun auth(@FieldMap params: Map<String, String>): Call<AuthModel>

    @GET("News")
    fun news(): Call<List<NewsModel>>

    @GET("Messages")
    fun messages(@Query("typeId") id: Int): Call<List<MessageItemModel>>

    @GET("Messages/{id}")
    fun message(@Path("id") id: Int): Call<MessageModel>

    @DELETE("Messages/{id}")
    fun deleteMessage(@Path("id") id: Int): Call<Unit>

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
        @Query("dateFrom") dateFrom: String
    ): Call<CurrentBalance>

    @GET("Requests")
    fun requests(): Call<List<RequestsModel>>

    @GET("Requests/Types")
    fun requestTypes(): Call<List<RequestTypeModel>>

    @GET("Requests/Addresses")
    fun requestAddresses(): Call<List<RequestAddressesModel>>

    @PUT("Requests")
    fun requestUpdate(@Body body: UpdateRequest): Call<Unit>

    @POST("Requests")
    fun requestAdd(@Body body: AddRequest): Call<String>

    @GET("Requests/{id}")
    fun requestGet(@Path("id") id: Int): Call<RequestModel>


    @DELETE("Requests/{id}")
    fun requestDelete(@Path("id") id: Int): Call<String>

    @GET("Balance/Invoices/{id}/Download")
    fun downloadLink(@Path("id") id: Int): Call<String>

    @Multipart
    @POST("Messages/ToManager")
    fun sendMessageToManager(
        @Query("model.body") body: String,
        @Query("model.title") title: String,
        @Part file: List<MultipartBody.Part>
    ): Call<Unit>

    @GET("Messages/Houses")
    fun houses(): Call<List<MessagesHousesModel>>

    @GET("Messages/Houses/{id}/Placements")
    fun placements(@Path("id") id: Int): Call<List<MessagesPlacementsModel>>

    @GET("Messages/Houses/Placements/{id}/Persons")
    fun persons(@Path("id") id: Int): Call<List<MessagesPersonsModel>>

    @Multipart
    @POST("Messages")
    fun messageToPerson(
        @Query("model.personId") personId: Int,
        @Query("model.body") body: String,
        @Query("model.title") title: String,
        @Part file: List<MultipartBody.Part>
    ): Call<Unit>

    @GET("Certificates")
    fun references(@Query("id") id: Int): Call<List<ReferenceLiteModel>>

    @POST("Certificates")
    fun addReferences(@Body certificateRequest: CertificateRequest): Call<Unit>

    @PUT("Certificates")
    fun updateReferences(@Body certificateRequest: CertificateRequest): Call<Unit>

    @GET("Certificates/{id}")
    fun reference(@Path("id") id: Int): Call<ReferencesFullModel>

    @GET("Certificates/Relatives")
    fun relatives(): Call<List<MessagesPersonsModel>>

    @POST("ForgotPassword")
    fun forgotPassword(@Query("email") email: String): Call<String>

    @GET("Voting")
    fun votingList(
        @Query("typeId") typeId: Int,
        @Query("id") id: Int
    ): Call<List<VoteModel>>

    @GET("Voting/Addresses")
    fun votingAddress(): Call<List<AddressModel>>
}