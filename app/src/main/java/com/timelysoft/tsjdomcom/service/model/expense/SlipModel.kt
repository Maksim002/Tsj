package com.timelysoft.tsjdomcom.service.model.expense

import com.google.gson.annotations.SerializedName

class SlipModel (
    @SerializedName("ManagerName")
    var managerName: String,

    @SerializedName("TypeName")
    var typeName: String?,

    @SerializedName("Id")
    var id: Int?,

    @SerializedName("ManagerId")
    var managerId: Int?,

    @SerializedName("Amount")
    var amount: Double?,

    @SerializedName("AmountType")
    var amountType: Int?,

    @SerializedName("OnDate")
    var onDate: String?,

    @SerializedName("Description")
    var description: String?
)