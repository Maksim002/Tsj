package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestTypeModel {
    @SerializedName("RequestTypeId")
    @Expose
    var requestTypeId: Int = 0
    @SerializedName("RequestTypeName")
    @Expose
    var requestTypeName: String = ""

    override fun toString(): String {
        return requestTypeName
    }
}