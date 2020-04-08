package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InvoicesAccounts(

    @SerializedName("Id")
    @Expose
    var id: Int,
    @SerializedName("Amount")
    @Expose
    var amount: Double,
    @SerializedName("Date")
    @Expose
    var date: String

)