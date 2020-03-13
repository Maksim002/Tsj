package com.example.tsj.ui.contact.fragments

import com.example.tsj.service.model.AddressModel

interface AccountsListener {
    fun getLicNumber(addressModel: AddressModel)
}