package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessagesPersonsModel {

    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("Name")
    @Expose
    lateinit var name: String

    override fun toString(): String {
        return name
    }


}
