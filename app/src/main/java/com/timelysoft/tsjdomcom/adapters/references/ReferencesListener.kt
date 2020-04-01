package com.timelysoft.tsjdomcom.adapters.references

import com.timelysoft.tsjdomcom.service.model.ReferenceLiteModel

interface ReferencesListener {
    fun onClickItem(item: ReferenceLiteModel)
}