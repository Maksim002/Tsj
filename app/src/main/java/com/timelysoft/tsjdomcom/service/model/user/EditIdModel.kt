package com.timelysoft.tsjdomcom.service.model.user

import com.google.gson.annotations.SerializedName

class EditIdModel (
    @SerializedName("Person")
    var person: PersonModel? = null,

    @SerializedName("Association")
    var association: String = ""
)