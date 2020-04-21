package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.SerializedName

class ModelStateModel {
    @SerializedName("Errors")
    var errors: List<String>? = null
}