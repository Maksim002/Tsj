package com.timelysoft.tsjdomcom.service.model.expense

import com.google.gson.annotations.SerializedName

class EntryAddModel (
    @SerializedName("ManagerId")
    var managerId: Int = 0,

    @SerializedName("Amount")
    var amount: Int = 0,

    @SerializedName("AmountType")
    var amountType: Int = 0,

    @SerializedName("OnDate")
    var onDate: String = "",

    @SerializedName("Description")
    var description: String = ""
)