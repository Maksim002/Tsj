package com.timelysoft.tsjdomcom.service.model.user

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel (
    @SerializedName("Users")
    @Expose
    var users: List<UserChairmanModel>,

    @SerializedName("Association")
    @Expose
    var association: String

)