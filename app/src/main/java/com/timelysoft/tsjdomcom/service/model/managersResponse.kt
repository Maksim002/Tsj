package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.SerializedName

class managersResponse(
    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String = ""
) {
    override fun toString(): String {
        return name
    }
}