package com.timelysoft.tsjdomcom.adapters.news

import java.io.Serializable

class AddNewsModel(
    var position: Int,
    var name: String
):Serializable {
    override fun toString(): String {
        return this.name
    }
}
