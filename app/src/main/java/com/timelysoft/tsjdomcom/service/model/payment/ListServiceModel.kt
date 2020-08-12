package com.timelysoft.tsjdomcom.service.model.payment

import com.google.gson.annotations.SerializedName

class ListServiceModel (
    @SerializedName("Id")
    var id: Int?,

    @SerializedName("Name")
    var name: String?
){
    override fun toString(): String {
        return name!!
    }
}