package com.example.tsj.adapters.bid

import com.example.tsj.model.BidModel
import com.example.tsj.service.model.RequestsModel
import java.text.FieldPosition

interface RequestClickItemListener {
    fun onClickRequest(item: RequestsModel)
}