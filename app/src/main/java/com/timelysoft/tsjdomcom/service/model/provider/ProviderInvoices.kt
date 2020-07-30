package com.timelysoft.tsjdomcom.service.model.provider

import com.google.gson.annotations.SerializedName

class ProviderInvoices (
    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String = ""
){
    override fun toString(): String {
        return name
    }
}