package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.SerializedName

class ManagersDownloadModel(
    @SerializedName("Message")
    var message: String = "",
    @SerializedName("ModelState")
    var modelStateModel: ModelStateModel? = null
)