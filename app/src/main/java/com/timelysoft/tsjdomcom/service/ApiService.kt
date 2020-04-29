package com.timelysoft.tsjdomcom.service

import com.timelysoft.tsjdomcom.service.model.MessageItemModel
import com.timelysoft.tsjdomcom.service.model.VoteModel
import com.timelysoft.tsjdomcom.service.model.*
import com.timelysoft.tsjdomcom.service.model.news.NewsCommentsModel
import com.timelysoft.tsjdomcom.service.model.news.NewsDetailModel
import com.timelysoft.tsjdomcom.service.model.news.NewsModel
import com.timelysoft.tsjdomcom.service.model.vote.VotingDetailModel
import com.timelysoft.tsjdomcom.service.request.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("Token")
    suspend fun auth(@FieldMap params: Map<String, String>): Response<AuthModel>

    @GET("News")
    suspend fun news(): Response<List<NewsModel>>

    @GET("Messages")
    suspend fun messages(@Query("typeId") id: Int): Response<List<MessageItemModel>>

    @GET("Messages/{id}")
    suspend fun message(@Path("id") id: Int): Response<MessageModel>

    @DELETE("Messages/{id}")
    suspend fun deleteMessage(@Path("id") id: Int): Response<Unit>

    @GET("Balance/Addresses")
    suspend fun addresses(): Response<List<AddressModel>>

    @GET("Balance/{id}/Services/Balance")
    suspend fun status(@Path("id") id: Int): Response<List<BalanceStatusModel>>

    @GET("Balance/Operations")
    suspend fun operations(): Response<List<OperationsModel>>

    @GET("Balance/Periods")
    suspend fun periods(): Response<PeriodsModel>

    @GET("Balance/{id}/Services")
    suspend fun services(@Path("id") id: Int): Response<List<ServicesModel>>

    @GET("Balance/CurrentBalance")
    suspend fun invoices(
        @Query("placementId") placementId: Int,
        @Query("serviceId") serviceId: Int,
        @Query("operationId") operationId: Int,
        @Query("dateFrom") dateFrom: String,
        @Query("dateTo") dateTo: String
    ): Response<CurrentBalance>

    @GET("Requests")
    suspend fun requests(): Response<List<RequestsModel>>

    @GET("Requests/Types")
    suspend fun requestTypes(): Response<List<RequestTypeModel>>

    @GET("Requests/Addresses")
    suspend fun requestAddresses(): Response<List<RequestAddressesModel>>

    @PUT("Requests")
    suspend fun requestUpdate(@Body body: UpdateRequest): Response<Unit>

    @POST("Requests")
    suspend fun requestAdd(@Body body: AddRequest): Response<Unit>

    @GET("Requests/{id}")
    suspend fun requestGet(@Path("id") id: Int): Response<RequestModel>

    @DELETE("Requests/{id}")
    suspend fun requestDelete(@Path("id") id: Int): Response<String>

    @GET("Balance/Invoices/{id}/Download")
    suspend fun downloadLink(@Path("id") id: Int): Response<String>

    @Multipart
    @POST("Messages/ToManager")
    suspend fun sendMessageToManager(
        @Query("model.body") body: String,
        @Query("model.title") title: String,
        @Part file: List<MultipartBody.Part>
    ): Response<Unit>

    @GET("Messages/Houses")
    suspend fun houses(): Response<List<MessagesHousesModel>>

    @GET("Messages/Houses/{id}/Placements")
    suspend fun placements(@Path("id") id: Int): Response<List<MessagesPlacementsModel>>

    @GET("Messages/Houses/Placements/{id}/Persons")
    suspend fun persons(@Path("id") id: Int): Response<List<MessagesPersonsModel>>
//

    @GET("Messages/Types")
    suspend fun messageTypes(): Response<List<MessagesPersonsModel>>

    @Multipart
    @POST("Messages")
    suspend fun messageToPerson(
        @Query("model.personId") personId: Int,
        @Query("model.body") body: String,
        @Query("model.title") title: String,
        @Part file: List<MultipartBody.Part>
    ): Response<Unit>

    @GET("Certificates")
    suspend fun references(@Query("id") id: Int): Response<List<ReferenceLiteModel>>

    @POST("Certificates")
    suspend fun addReferences(@Body certificateRequest: CertificateRequest): Response<Unit>

    @PUT("Certificates")
    suspend fun updateReferences(@Body certificateRequest: CertificateRequest): Response<Unit>

    @GET("Certificates/{id}")
    suspend fun reference(@Path("id") id: Int): Response<ReferencesFullModel>

    @GET("Certificates/Relatives")
    suspend fun relatives(): Response<List<MessagesPersonsModel>>

    @POST("ForgotPassword")
    suspend fun forgotPassword(@Query("email") email: String): Response<String>

    @GET("Voting")
    suspend fun votes(
        @Query("typeId") typeId: Int,
        @Query("id") id: Int
    ): Response<List<VoteModel>>

    @GET("Voting/Addresses")
    suspend fun votingAddress(): Response<List<AddressModel>>

    @GET("Messages/{id}/Reply")
    suspend fun reply(@Path("id") id: Int): Response<ReplyModel>

    @GET("Voting/Types")
    suspend fun votingType(): Response<List<MessagesPersonsModel>>

    @GET("Voting/{id}/Variants")
    suspend fun votingVariants(@Path("id") id: Int): Response<List<MessagesPersonsModel>>

    @POST("Voting")
    suspend fun votingPost(@Body body: VotingRequest): Response<String>

    @GET("Voting/{id}")
    suspend fun votingDetail(@Path("id") id: Int): Response<VotingDetailModel>

    @POST("RequestForConnection")
    suspend fun requestForConnect(@Body body: RequestForConnectModel): Response<String>

    @GET("News/{id}")
    suspend fun newsDetail(@Path("id") id: Int): Response<NewsDetailModel>

    @GET("News/{id}/Comments")
    suspend fun newsComment(@Path("id") id: Int): Response<List<NewsCommentsModel>>

    @POST("News/Comments")
    suspend fun newsCommentPost(@Body body: NewsCommentRequest): Response<String>

    @DELETE("News/Comments/{id}")
    suspend fun newsCommentDelete(@Path("id") id: Int): Response<String>

    @POST("ChangePassword")
    suspend fun changePassword(@Body model: ChangePasswordModel): Response<Unit>

    @POST("Contact/Feedback")
    suspend fun sendFeedback(@Body model: FeedbackRequest): Response<String>


    @PUT("FirebaseTokens")
    suspend fun sendFirebaseToken(@Body model: FirebaseTokenModel): Response<Unit>

    @GET("Certificates/Managers/{id}")
    suspend fun managers(@Path("id") id: Int): Response<List<ManagerResponse>>

    @GET("Certificates/{helpId}/Managers/{chairmanId}/Download")
    suspend fun downloadCertificate(
        @Path("helpId") helpId: Int,
        @Path("chairmanId") chairmanId: Int
    ): Response<String>
}

