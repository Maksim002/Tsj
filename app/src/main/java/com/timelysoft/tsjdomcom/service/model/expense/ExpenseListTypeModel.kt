package com.timelysoft.tsjdomcom.service.model.expense

import com.google.gson.annotations.SerializedName

class ExpenseListTypeModel (
    @SerializedName("Id")
    var id: Int?,

    @SerializedName("Name")
    var name: String?

)