package com.timelysoft.tsjdomcom.service.model.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserRequestModel (
    @SerializedName("ProviderName")
    var providerName: String = "",

    @SerializedName("StatusDate")
    var statusDate: String = "",

    @SerializedName("IsEditable")
    var isEditable: Boolean = false,

    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("CreatedDate")
    var createdDate: String = "",

    @SerializedName("ShortAddress")
    var shortAddress: String = "",

    @SerializedName("StatusName")
    var statusName: String = "",

    @SerializedName("RepairDesc")
    var repairDesc: Any = 0,

    @SerializedName("Description")
    var description: String = ""
)