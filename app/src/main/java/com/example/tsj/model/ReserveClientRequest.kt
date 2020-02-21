package com.example.tsj.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReserveClientRequest {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("phoneNumbers")
    @Expose
    var phoneNumbers: List<String>? = null

}