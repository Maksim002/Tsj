package com.timelysoft.tsjdomcom.adapters.provider

import com.timelysoft.tsjdomcom.service.model.provider.ProviderModel

interface ProviderListener {
    fun editClick(position: Int, item: ProviderModel)
    fun clearProviderClick(item: ProviderModel)
}