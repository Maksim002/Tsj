package com.timelysoft.tsjdomcom.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentBalance (
    @SerializedName("Balance")
    @Expose
    var balance: Double,
    @SerializedName("InvoicesHistory")
    @Expose
    var invoicesHistory: List<InvoicesAccounts>,
    @SerializedName("PaymentsHistory")
    @Expose
    var paymentsHistory: List<PaymentHistory>
)