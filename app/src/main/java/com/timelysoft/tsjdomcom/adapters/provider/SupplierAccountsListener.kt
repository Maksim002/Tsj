package com.timelysoft.tsjdomcom.adapters.provider

import com.timelysoft.tsjdomcom.service.model.provider.SupplierAccountsModel

interface SupplierAccountsListener {
    fun supplierAccountsOnClick(item: SupplierAccountsModel)
}