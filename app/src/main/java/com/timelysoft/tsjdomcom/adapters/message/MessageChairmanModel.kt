package com.timelysoft.tsjdomcom.adapters.message

import java.io.Serializable

class MessageChairmanModel (
    var position: Int,
    var name: String
): Serializable {
    override fun toString(): String {
        return this.name
    }
}
