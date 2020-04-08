package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChangePasswordModel {
    @SerializedName("OldPassword")
    @Expose
    var oldPassword: String = ""
    @SerializedName("NewPassword")
    @Expose
    var newPassword: String = ""
    @SerializedName("ConfirmPassword")
    @Expose
    var confirmPassword: String = ""
}