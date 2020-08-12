package com.timelysoft.tsjdomcom.service.model.payment

import com.google.gson.annotations.SerializedName

class InformationTsjModel (
    @SerializedName("Name")
    var name: String?,

    @SerializedName("Address")
    var address: String?

)