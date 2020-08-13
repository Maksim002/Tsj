package com.timelysoft.tsjdomcom.service.model.expense

import com.google.gson.annotations.SerializedName

class ExpenseExpensesReceiptsModel (
    @SerializedName("Total")
    var total: Double?,

    @SerializedName("Slips")
    var slips: List<SlipModel>?

)