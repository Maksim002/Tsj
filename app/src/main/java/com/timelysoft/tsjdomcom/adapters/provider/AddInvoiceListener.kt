package com.timelysoft.tsjdomcom.adapters.provider

import com.timelysoft.tsjdomcom.service.model.provider.FileModel

interface AddInvoiceListener{
    fun clearClickListener(position: Int, item: FileModel)
}