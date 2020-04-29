package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServicesModel {
    @SerializedName("ServiceId")
    @Expose
    var serviceId: Int = 0
    @SerializedName("ServiceName")
    @Expose
    var serviceName: String = ""
    override fun toString(): String {
        return serviceName.toString()
    }
}
