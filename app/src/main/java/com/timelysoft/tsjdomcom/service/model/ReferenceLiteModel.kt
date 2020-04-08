package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReferenceLiteModel {
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("CertificateNumber")
    @Expose
    var certificateNumber: Int? = null
    @SerializedName("ForDate")
    @Expose
    var forDate: String = ""
    @SerializedName("CertificateIssued")
    @Expose
    var certificateIssued: Boolean = false
    @SerializedName("FullName")
    @Expose
    var fullName: String = ""

}