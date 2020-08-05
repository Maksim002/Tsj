package com.timelysoft.tsjdomcom.service.model.request

import com.google.gson.annotations.SerializedName

class UserRequestEdit (
    @SerializedName("Id")
    var id: Int,

    @SerializedName("StatusId")
    var statusId: Int,

    @SerializedName("ClosedDate")
    var closedDate: String ,

    @SerializedName("RepairDesc")
    var repairDesc: String
)