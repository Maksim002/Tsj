package com.timelysoft.tsjdomcom.service.model.provider

import com.google.gson.annotations.SerializedName

class SupplierAccountsModel (
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
    var countersValue: Any = "null",

    @SerializedName("PaymentAmount")
    var paymentAmount: Any? = null
)