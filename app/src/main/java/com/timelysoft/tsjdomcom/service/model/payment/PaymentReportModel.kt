package com.timelysoft.tsjdomcom.service.model.payment

import com.google.gson.annotations.SerializedName

class PaymentReportModel (
    @SerializedName("Address")
    var address: Any?,

    @SerializedName("PlacementNumber")
    var placementNumber: Any?,

    @SerializedName("ServiceName")
    var serviceName: String?,

    @SerializedName("PayType")
    var payType: Any?,

    @SerializedName("Sum")
    var sum: Int?,

    @SerializedName("CreatedDate")
    var createdDate: Any?
)