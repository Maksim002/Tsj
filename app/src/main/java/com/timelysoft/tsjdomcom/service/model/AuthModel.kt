package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AuthModel {
    @SerializedName("access_token")
    @Expose
    var accessToken: String = ""
    @SerializedName("token_type")
    @Expose
    var tokenType: String = ""
    @SerializedName("expires_in")
    @Expose
    var expiresIn: Int = 0
    @SerializedName("userName")
    @Expose
    var userName: String = ""
    @SerializedName(".issued")
    @Expose
    var issued: String = ""
    @SerializedName(".expires")
    @Expose
    var expires: String = ""
}
