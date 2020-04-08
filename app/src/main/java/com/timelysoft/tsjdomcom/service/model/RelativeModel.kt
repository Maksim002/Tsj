package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.timelysoft.tsjdomcom.utils.MyUtils.toMyDate

class RelativeModel {
    @SerializedName("Id")
    @Expose
    var id: Int? = null
    @SerializedName("RelativeId")
    @Expose
    var relativeId: Int? = null
    @SerializedName("DateOfBirth")
    @Expose
    var dateOfBirth: String = ""
    @SerializedName("FullName")
    @Expose
    var fullName: String = ""
    @SerializedName("RelativeName")
    @Expose
    var relative: String = ""

    val date: String
        get() = toMyDate(dateOfBirth)

    constructor(
        relativeId: Int?,
        dateOfBirth: String,
        fullName: String,
        relative: String
    ) {
        this.relativeId = relativeId
        this.fullName = fullName
        this.relative = relative
        this.dateOfBirth = dateOfBirth
    }

}