package com.timelysoft.tsjdomcom.adapters.bid

import com.timelysoft.tsjdomcom.service.model.RequestsModel

interface RequestClickItemListener {
    fun onClickRequest(item: RequestsModel)
}