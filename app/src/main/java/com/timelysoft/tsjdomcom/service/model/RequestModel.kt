package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestModel {
    @SerializedName("Address")
    @Expose
    var address: String = ""
    @SerializedName("Entrance")
    @Expose
    var entrance: Int = 0
    @SerializedName("Floor")
    @Expose
    var floor: Int = 0

    @SerializedName("CreatedDate")
    @Expose
    var createdDate: String = ""

    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("RequestTypeName")
    @Expose
    var requestTypeName: String = ""

    @SerializedName("StatusName")
    @Expose
    var statusName: String = ""

    @SerializedName("StatusDate")
    @Expose
    var statusDate: String = ""

    @SerializedName("IsEditableAndCloseable")
    @Expose
    var editableAndCloseable: Boolean = false
    @SerializedName("Description")
    @Expose
    var description: String = ""

}