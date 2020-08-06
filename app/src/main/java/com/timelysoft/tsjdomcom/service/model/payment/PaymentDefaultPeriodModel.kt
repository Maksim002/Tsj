package com.timelysoft.tsjdomcom.service.model.payment


import com.google.gson.annotations.SerializedName

class PaymentDefaultPeriodModel (
    @SerializedName("From")
    var from: String = "",

    @SerializedName("To")
    var to: String = ""
)