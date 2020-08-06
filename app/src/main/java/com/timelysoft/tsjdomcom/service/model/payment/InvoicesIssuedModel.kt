package com.timelysoft.tsjdomcom.service.model.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InvoicesIssuedModel (
    @SerializedName("Name")
    @Expose
    var name: String = "",

    @SerializedName("Sum")
    @Expose
    var sum: Int = 0,

    @SerializedName("OnDate")
    @Expose
    var onDate: String = "",

    @SerializedName("ServiceName")
    @Expose
    var serviceName: String = "",

    @SerializedName("Address")
    @Expose
    var address: String = ""
)