package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DetailsModel {

    @SerializedName("Address")
    @Expose
    var address: String? = null
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
    var requestTypeName: String? = null
    @SerializedName("IsEditableAndCloseable")
    @Expose
    var isEditableAndCloseable: Boolean? = null
    @SerializedName("Description")
    @Expose
    var description: String? = null

}
