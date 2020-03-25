package com.example.tsj.adapters.bid

import com.example.tsj.service.model.RequestsModel

interface RequestClickItemListener {
    fun onClickRequest(item: RequestsModel)
}