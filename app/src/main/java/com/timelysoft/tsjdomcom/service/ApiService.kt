package com.timelysoft.tsjdomcom.service

import com.timelysoft.tsjdomcom.service.model.MessageItemModel
import com.timelysoft.tsjdomcom.service.model.VoteModel
import com.timelysoft.tsjdomcom.service.model.*
import com.timelysoft.tsjdomcom.service.model.counter.DebtsModel
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseExpensesReceiptsModel
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTSJModel
import com.timelysoft.tsjdomcom.service.model.expense.ExpenseListTypeModel
import com.timelysoft.tsjdomcom.service.model.news.NewsCommentsModel
import com.timelysoft.tsjdomcom.service.model.news.NewsDetailModel
import com.timelysoft.tsjdomcom.service.model.news.NewsModel
import com.timelysoft.tsjdomcom.service.model.payment.*
import com.timelysoft.tsjdomcom.service.model.provider.*
import com.timelysoft.tsjdomcom.service.model.request.*
import com.timelysoft.tsjdomcom.service.model.service.AssociationServicesModel
import com.timelysoft.tsjdomcom.service.model.service.CreateServiceModel
import com.timelysoft.tsjdomcom.service.model.service.CreateTypeService
import com.timelysoft.tsjdomcom.service.model.service.PeriodServiceModel
import com.timelysoft.tsjdomcom.service.model.user.EditIdModel
import com.timelysoft.tsjdomcom.service.model.user.UserModel
import com.timelysoft.tsjdomcom.service.model.vote.VotingDetailModel
import com.timelysoft.tsjdomcom.service.request.*
import com.timelysoft.tsjdomcom.service.request.provider.CreateSupplier
import com.timelysoft.tsjdomcom.service.request.provider.ProviderEdit
import com.timelysoft.tsjdomcom.service.request.user.Edit
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*


interface ApiService {

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("Token")
    suspend fun auth(@FieldMap params: Map<String, String>): Response<AuthModel>

    @GET("News/Chairman")
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

    @GET("AssociationUsers")
    suspend fun user(): Response<UserModel>

    @PUT("AssociationUsers")
    suspend fun edit(@Body body: Edit): Response<String>

    @GET("Providers")
    suspend fun provider(): Response<ArrayList<ProviderModel>>

    @POST("Providers")
    suspend fun createSupplier(@Body body: CreateSupplier): Response<Unit>

    @DELETE("Providers/{id}")
    suspend fun providerDelete(@Path("id") id: Int): Response<String>

    @PUT("Providers")
    suspend fun providerEdit(@Body body: ProviderEdit): Response<Unit>

    @GET("Providers/{id}")
    suspend fun providerId(@Path("id") id: Int): Response<ProviderIdModel>

    @GET("AssociationUsers/{id}")
    suspend fun editId(@Path("id") id: Int): Response<EditIdModel>

    @GET("ProviderInvoices")
    suspend fun supplierAccounts(
        @Query("from") dataFrom: String,
        @Query("to") dataTo: String,
        @Query("providerId") providerId: Int
    ): Response<ArrayList<SupplierAccountsModel>>

    @GET("ProviderInvoices/Providers")
    suspend fun providerInvoices(): Response<ArrayList<ProviderInvoices>>

    @DELETE("ProviderInvoices/{id}")
    suspend fun supplierAccountsDelete(@Path("id") id: Int): Response<String>

    @GET("ProviderInvoices/{id}")
    suspend fun providerInvoicesId(@Path("id") id: Int): Response<ProviderInvoicesIdModel>

    @Multipart
    @PUT("ProviderInvoices")
    suspend fun providerInvoicesEdit(
        @Query("id") id: Int,
        @Query("service") service: String,
        @Query("providerId") providerId: Int,
        @Query("date") date: String,
        @Query("countersValue") countersValue: Int,
        @Query("paymentAmount") paymentAmount: Int,
        @Part file: ArrayList<MultipartBody.Part>
    ): Response<Unit>

    @Multipart
    @POST("ProviderInvoices")
    suspend fun addInvoice(
        @Query("service") service: String,
        @Query("providerId") providerId: Int,
        @Query("date") date: String,
        @Query("countersValue") countersValue: String,
        @Query("paymentAmount") paymentAmount: String,
        @Part file: ArrayList<MultipartBody.Part>
    ): Response<Unit>

    @GET("Requests/Chairman")
    suspend fun listUser(
        @Query("from") dataFrom: String,
        @Query("to") dataTo: String,
        @Query("typeId") typeId: Int?
    ): Response<ArrayList<UserRequestModel>>

    @GET("Requests/Types/WithAll")
    suspend fun listUserType(): Response<ArrayList<UserRequestTypeModel>>

    @GET("Requests/Chairman/Download")
    suspend fun userRequestSave(
        @Query("from") dataFrom: String,
        @Query("to") dataTo: String,
        @Query("typeId") typeId: Int?
    ): Response<String>

    @GET("Requests/Chairman/{id}")
    suspend fun listUserView(
        @Path("id") id: Int
    ): Response<ListUserView>

    @PUT("Requests/Chairman")
    suspend fun userRequestEdit(@Body body: UserRequestEdit): Response<Unit>

    @GET("Requests/Chairman/Statuses")
    suspend fun listUserStatus(): Response<ArrayList<ListUserStatus>>

    @PATCH("Requests/Chairman/{id}/Providers")
    suspend fun linkSupplier(
        @Path("id") id: Int,
        @Query("pid") pid: Int
    ): Response<Unit>

    @GET("Requests/Chairman/Providers")
    suspend fun userRequestProvider(): Response<ArrayList<ProviderInvoices>>

    @GET("AssociationServices")
    suspend fun listService(): Response<AssociationServicesModel>

    @GET("Invoices")
    suspend fun invoicesIssued(
        @Query("from") dataFrom: String,
        @Query("to") dataTo: String
    ): Response<ArrayList<InvoicesIssuedModel>>

    @GET("Invoices/DefaultPeriod")
    suspend fun paymentDefaultPeriod(): Response<PaymentDefaultPeriodModel>

    @POST("AssociationServices")
    suspend fun createService(@Body body: CreateServiceModel): Response<Unit>

    @GET("AssociationServices/Periods")
    suspend fun createPeriodService(): Response<ArrayList<PeriodServiceModel>>

    @GET("AssociationServices/Types")
    suspend fun createTypeService(): Response<ArrayList<CreateTypeService>>

    @GET("Invoices/BaseTemplate")
    suspend fun paymentDownloadTemplate(): Response<String>

    @Multipart
    @PUT("Invoices/Template")
    suspend fun paymentDownloadSave(
        @Part file: ArrayList<MultipartBody.Part>): Response<Unit>

    @GET("PaymentManagement/Debts")
    suspend fun counterListDebts(): Response<DebtsModel>

    @Multipart
    @POST("News")
    suspend fun newsAddMessage(
        @Part file: ArrayList<MultipartBody.Part>?,
        @Query ("content") content: String,
        @Query ("title") title: String
    ): Response<Unit>

    @GET("PaymentManagement/Association")
    suspend fun paymentInformationTsj(): Response<InformationTsjModel>

    @GET("PaymentManagement/UnbalancedPayments")
    suspend fun paymentPaymentReport(
        @Query ("from") from: String,
        @Query ("to") to: String,
        @Query ("serviceId") serviceId: Int?
    ): Response<ArrayList<PaymentReportModel>>

    @GET("PaymentManagement/Services")
    suspend fun paymentListService(): Response<ArrayList<ListServiceModel>>

    @GET("PaymentManagement/DefaultPeriod")
    suspend fun paymentDateDefault(): Response<DefaultPeriodModel>

    @GET("PaymentManagement/AllPayments")
    suspend fun paymentListPayment(
        @Query ("from") from: String,
        @Query ("to") to: String,
        @Query ("serviceId") serviceId: Int?
    ): Response<ArrayList<PaymentReportModel>>

    @GET("PaymentManagement/UnbalancedPayments/Download")
    suspend fun paymentSave(
        @Query ("from") from: String,
        @Query ("to") to: String,
        @Query ("serviceId") serviceId: Int?
    ): Response<String>

    @GET("CreditAndDebitSlips/Associations")
    suspend fun expenseListTSJ(): Response<ArrayList<ExpenseListTSJModel>>

    @GET("CreditAndDebitSlips/Types")
    suspend fun expenseListType(): Response<ArrayList<ExpenseListTypeModel>>

    @GET("CreditAndDebitSlips")
    suspend fun expenseExpensesReceipts(@Query("id") id: Int): Response<ExpenseExpensesReceiptsModel>
}

