package com.timelysoft.tsjdomcom.service.model.request

import com.google.gson.annotations.SerializedName

class ListUserStatus (
    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String = ""

){
    override fun toString(): String {
        return name
    }
}