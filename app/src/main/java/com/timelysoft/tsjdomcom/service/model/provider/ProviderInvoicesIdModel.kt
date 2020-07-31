package com.timelysoft.tsjdomcom.service.model.provider

import com.google.gson.annotations.SerializedName

class ProviderInvoicesIdModel (
    @SerializedName("Files")
    var files: List<FileModel> = arrayListOf(),

    @SerializedName("ProviderName")
    var providerName: String = "",

    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Service")
    var service: String = "",

    @SerializedName("ProviderId")
    var providerId: Int = 0,

    @SerializedName("Date")
    var date: String = "",

    @SerializedName("CountersValue")
    var countersValue: Int = 0,

    @SerializedName("PaymentAmount")
    var paymentAmount: Int = 0

)