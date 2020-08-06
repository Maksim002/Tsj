package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Association (
    @SerializedName("Name")
    @Expose
    var name: String? = null,

    @SerializedName("Address")
    @Expose
    var address: String? = null

)