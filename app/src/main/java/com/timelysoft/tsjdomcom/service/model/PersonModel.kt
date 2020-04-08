package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonModel {
    @SerializedName("Id")
    @Expose
    var id: Int? = null
    @SerializedName("DateOfBirth")
    @Expose
    var dateOfBirth: String = ""
    @SerializedName("FullName")
    @Expose
    var fullName: String = ""

}