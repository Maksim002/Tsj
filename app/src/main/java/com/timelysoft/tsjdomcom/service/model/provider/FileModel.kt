package com.timelysoft.tsjdomcom.service.model.provider

import com.google.gson.annotations.SerializedName

class FileModel (
    @SerializedName("Name")
    var name: String = "",

    @SerializedName("Link")
    var link: String = ""
)