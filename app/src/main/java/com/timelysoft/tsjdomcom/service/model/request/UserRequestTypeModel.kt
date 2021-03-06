package com.timelysoft.tsjdomcom.service.model.request

import com.google.gson.annotations.SerializedName

class UserRequestTypeModel (
    @SerializedName("Id")
    var id: Int?,

    @SerializedName("Name")
    var name: String

){
    override fun toString(): String {
        return name
    }
}