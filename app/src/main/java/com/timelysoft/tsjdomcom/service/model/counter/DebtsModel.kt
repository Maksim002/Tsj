package com.timelysoft.tsjdomcom.service.model.counter

import com.google.gson.annotations.SerializedName

class DebtsModel (
    @SerializedName("Association")
    var association: AssociationCounterModel?,

    @SerializedName("Debts")
    var debts: List<DebtModel>?
)