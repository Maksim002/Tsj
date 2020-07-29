package com.timelysoft.tsjdomcom.service.model.user

import com.google.gson.annotations.SerializedName

class PersonModel (
    @SerializedName("FirstName")
    var firstName: String = "",

    @SerializedName("MiddleName")
    var middleName: String = "",

    @SerializedName("LastName")
    var lastName: String = "",

    @SerializedName("NewPassword")
    var newPassword: String = "",

    @SerializedName("ConfirmPassword")
    var confirmPassword: String = "",

    @SerializedName("Id")
    var id: Int = 0,

    @SerializedName("Email")
    var email: String = ""

)