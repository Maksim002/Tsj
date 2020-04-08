package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailsModel {

    @SerializedName("Address")
    @Expose
    var address: String = ""
    @SerializedName("Entrance")
    @Expose
    var entrance: Int = 0
    @SerializedName("Floor")
    @Expose
    var floor: Int = 0
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("RequestTypeName")
    @Expose
    var requestTypeName: String = ""
    @SerializedName("IsEditableAndCloseable")
    @Expose
    var isEditableAndCloseable: Boolean = false
    @SerializedName("Description")
    @Expose
    var description: String = ""

}
