package com.timelysoft.tsjdomcom.service.model.counter

import com.google.gson.annotations.SerializedName

class AssociationCounterModel (
    @SerializedName("Name")
    var name: String?,

    @SerializedName("Address")
    var address: String?
)