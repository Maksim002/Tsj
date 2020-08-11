package com.timelysoft.tsjdomcom.service.model.counter

import com.google.gson.annotations.SerializedName

class DebtModel (
    @SerializedName("Address")
    var address: Any?,

    @SerializedName("PlacementNumber")
    var placementNumber: String?,

    @SerializedName("PaymentSum")
    var paymentSum: Double?
)