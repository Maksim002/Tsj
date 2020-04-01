package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OperationsModel {
    @SerializedName("OperationId")
    @Expose
    var operationId: Int? = null
    @SerializedName("OperationName")
    @Expose
    var operationName: String? = null

}
