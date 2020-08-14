package com.timelysoft.tsjdomcom.service.model.expense

import com.google.gson.annotations.SerializedName

class ChangeEditModel (
    @SerializedName("Id")
    var id: Int? = null,

    @SerializedName("ManagerId")
    var managerId: Int? = null,

    @SerializedName("Amount")
    var amount: Double? = null,

    @SerializedName("AmountType")
    var amountType: Int? = null,

    @SerializedName("OnDate")
    var onDate: String? = null,

    @SerializedName("Description")
    var description: String? = null
)