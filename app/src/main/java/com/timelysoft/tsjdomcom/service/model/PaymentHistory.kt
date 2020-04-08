package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentHistory (
    @SerializedName("PersonName")
    @Expose
    var personName: String,
    @SerializedName("Amount")
    @Expose
    var amount: Int,
    @SerializedName("Date")
    @Expose
    var date: String
    )
