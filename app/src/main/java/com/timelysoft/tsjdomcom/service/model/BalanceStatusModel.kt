package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BalanceStatusModel {

    @SerializedName("ServiceName")
    @Expose
    var serviceName: String? = null
    @SerializedName("Balance")
    @Expose
    var balance: Double? = null
}
