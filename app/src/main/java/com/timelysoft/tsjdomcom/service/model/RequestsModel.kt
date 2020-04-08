package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestsModel {
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("DateArrival")
    @Expose
    var dateArrival: String = ""
    @SerializedName("RequestTypeName")
    @Expose
    var requestTypeName: String = ""
    @SerializedName("RepairDesc")
    @Expose
    var repairDesc: String = ""
    @SerializedName("StatusName")
    @Expose
    var statusName: String = ""
    @SerializedName("StatusDate")
    @Expose
    var statusDate: String = ""
    @SerializedName("ProviderName")
    @Expose
    var providerName: String = ""
    @SerializedName("IsEditableAndCloseable")
    @Expose
    var isEditableAndCloseable: Boolean = false
    @SerializedName("Description")
    @Expose
    var description: String = ""

}