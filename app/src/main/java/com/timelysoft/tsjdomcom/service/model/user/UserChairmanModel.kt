package com.timelysoft.tsjdomcom.service.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserChairmanModel (
    @SerializedName("Name")
    @Expose
    var name: String,

    @SerializedName("RoleType")
    @Expose
    var roleType: String,

    @SerializedName("Id")
    @Expose
    var id: Int,

    @SerializedName("Email")
    @Expose
    var email: String

): Serializable