package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.SerializedName

class PeriodServiceModel (
    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Name")
    var name: String = ""

){
    override fun toString(): String {
        return name
    }
}