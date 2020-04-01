package com.timelysoft.tsjdomcom.ui.contact.fragments

import com.timelysoft.tsjdomcom.service.model.AddressModel

interface AccountsListener {
    fun getLicNumber(addressModel: AddressModel)
}