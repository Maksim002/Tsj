package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestModel {
    @SerializedName("Address")
    @Expose
    var address: String = ""
    @SerializedName("Entrance")
    @Expose
    var entrance: Int? = null
    @SerializedName("Floor")
    @Expose
    var floor: Int? = null
    @SerializedName("Id")
    @Expose
    var id: Int? = null
    @SerializedName("RequestTypeName")
    @Expose
    var requestTypeName: String = ""
    @SerializedName("IsEditableAndCloseable")
    @Expose
    var editableAndCloseable: Boolean? = null
    @SerializedName("Description")
    @Expose
    var description: String = ""

}