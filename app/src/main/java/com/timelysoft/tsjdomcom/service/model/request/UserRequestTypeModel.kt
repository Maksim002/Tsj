package com.timelysoft.tsjdomcom.service.model.request

import com.google.gson.annotations.SerializedName

class UserRequestTypeModel (
    @SerializedName("RequestTypeId")
    var requestTypeId: Int = 0,

    @SerializedName("RequestTypeName")
    var requestTypeName: String = ""

) {
    override fun toString(): String {
        return requestTypeName
    }
}