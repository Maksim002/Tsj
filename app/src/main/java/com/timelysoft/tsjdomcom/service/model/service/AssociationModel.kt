package com.timelysoft.tsjdomcom.service.model.service

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AssociationModel (
    @SerializedName("Name")
    @Expose
    var name: String = "",

    @SerializedName("Address")
    @Expose
    var address: String = ""
)