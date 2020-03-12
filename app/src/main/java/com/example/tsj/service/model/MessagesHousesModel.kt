package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessagesHousesModel {
    @SerializedName("Id")
    @Expose
    var id: Int = 0
    @SerializedName("Address")
    @Expose
    lateinit var address: String
}
