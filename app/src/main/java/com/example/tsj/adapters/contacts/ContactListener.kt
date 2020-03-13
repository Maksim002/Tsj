package com.example.tsj.adapters.contacts

import com.example.tsj.service.model.AddressModel

interface ContactListener {
    fun onClickItem(addressModel: AddressModel)
}