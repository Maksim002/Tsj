package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaymentHistory (
    @SerializedName("PersonName")
    @Expose
    var personName: String? = null,
    @SerializedName("Amount")
    @Expose
    var amount: Int? = null,
    @SerializedName("Date")
    @Expose
    var date: String? = null
    )
