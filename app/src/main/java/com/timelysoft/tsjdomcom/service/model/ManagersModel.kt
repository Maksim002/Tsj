package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.SerializedName

class ManagersModel(
    @SerializedName("Id")
    var id: Int? = null,

    @SerializedName("Name")
    var name: String = ""
) {
    override fun toString(): String {
        return name
    }
}