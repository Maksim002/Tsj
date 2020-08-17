package com.timelysoft.tsjdomcom.adapters.expense

import com.timelysoft.tsjdomcom.service.model.expense.SlipModel

interface ComingsClickListener {
    fun comingsOnClickListener(item: SlipModel)
    fun comingsOnClickListener(item: SlipModel, position: Int)
}