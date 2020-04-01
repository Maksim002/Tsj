package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessagesPlacementsModel {
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("Number")
    @Expose
    lateinit var number: String

}
