package com.example.tsj.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentBalance (
    @SerializedName("Balance")
    @Expose
    var balance: Double? = null,
    @SerializedName("InvoicesHistory")
    @Expose
    var invoicesHistory: List<InvoicesAccounts>? = null,
    @SerializedName("PaymentsHistory")
    @Expose
    var paymentsHistory: List<PaymentHistory>? = null
)