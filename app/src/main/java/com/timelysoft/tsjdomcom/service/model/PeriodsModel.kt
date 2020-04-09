package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PeriodsModel {
    @SerializedName("From")
    @Expose
    var from: String = ""
    @SerializedName("To")
    @Expose
    var to: String = ""
}
