package com.timelysoft.tsjdomcom.service.model.payment

import com.google.gson.annotations.SerializedName

class ListPaymentModel (
    @SerializedName("Address")
    var address: String?,

    @SerializedName("ServiceName")
    var serviceName: String?,

    @SerializedName("Sum")
    var sum: Int?,

    @SerializedName("EndDate")
    var endDate: String?,

    @SerializedName("IsCounted")
    var isCounted: String?,

    @SerializedName("PayType")
    var payType: String?
)