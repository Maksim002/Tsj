package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InvoicesAccounts(

    @SerializedName("Id")
    @Expose
    var id: Int? = null,
    @SerializedName("Amount")
    @Expose
    var amount: Double? = null,
    @SerializedName("Date")
    @Expose
    var date: String? = null

)