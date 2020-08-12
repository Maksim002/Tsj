package com.timelysoft.tsjdomcom.service.model.payment

import com.google.gson.annotations.SerializedName

class DefaultPeriodModel (
    @SerializedName("From")
    var from: String = "",

    @SerializedName("To")
    var to: String = ""
)