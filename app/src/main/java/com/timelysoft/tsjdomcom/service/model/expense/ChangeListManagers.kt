package com.timelysoft.tsjdomcom.service.model.expense

import com.google.gson.annotations.SerializedName

class ChangeListManagers (
    @SerializedName("Id")
    var id: Int?,

    @SerializedName("Name")
    var name: String?
){
    override fun toString(): String {
        return name!!
    }
}