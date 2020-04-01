package com.timelysoft.tsjdomcom.adapters.contacts

import com.timelysoft.tsjdomcom.service.model.AddressModel

interface ContactListener {
    fun onClickItem(addressModel: AddressModel)
}