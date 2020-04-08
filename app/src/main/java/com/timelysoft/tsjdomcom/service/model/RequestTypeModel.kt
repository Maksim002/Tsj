package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestTypeModel {
    @SerializedName("RequestTypeId")
    @Expose
    var requestTypeId: Int? = null
    @SerializedName("RequestTypeName")
    @Expose
    var requestTypeName: String = ""

    override fun toString(): String {
        return requestTypeName
    }
}