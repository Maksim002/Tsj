package com.timelysoft.tsjdomcom.adapters.provider

import java.io.Serializable

class ProviderModel : Serializable {
    val id: Int
    val title: String
    val type: String
    val address: String
    val inn: String
    val okpo: String
    val telephone: String
    val account: String
    val delet: String
    val eit: String
    constructor():this(0,"","","","","","","","",""){
    }
    constructor(
        id: Int,
        title: String,
        type: String,
        address: String,
        inn: String,
        okpo: String,
        telephone: String,
        account: String,
        delet: String,
        eit: String
    ) {
        this.address = address
        this.id = id
        this.title = title
        this.type = type
        this.inn = inn
        this.okpo = okpo
        this.telephone = telephone
        this.account = account
        this.delet = delet
        this.eit = eit
    }
}