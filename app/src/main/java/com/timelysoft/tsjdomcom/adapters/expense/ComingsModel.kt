package com.timelysoft.tsjdomcom.adapters.expense

import java.io.Serializable

class ComingsModel(
    var address: String
) : Serializable {

    override fun toString(): String {
        return address
    }
}
