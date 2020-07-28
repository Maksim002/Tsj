package com.timelysoft.tsjdomcom.service.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class EditModel (
    @SerializedName("FirstName")
    @Expose
    var firstName: String,

    @SerializedName("MiddleName")
    @Expose
    var middleName: String,

    @SerializedName("LastName")
    @Expose
    var lastName: String,

    @SerializedName("NewPassword")
    @Expose
    var newPassword: String,

    @SerializedName("ConfirmPassword")
    @Expose
    var confirmPassword: String,

    @SerializedName("Id")
    @Expose
    var id: Int,

    @SerializedName("Email")
    @Expose
    var email: String
)