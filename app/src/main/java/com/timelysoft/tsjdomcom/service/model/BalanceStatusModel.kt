package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BalanceStatusModel {

    @SerializedName("ServiceName")
    @Expose
    var serviceName: String = ""
    @SerializedName("Balance")
    @Expose
    var balance: Double = 0.0
}
