package com.timelysoft.tsjdomcom.adapters.message

import com.timelysoft.tsjdomcom.service.model.MessageItemModel

interface MessageClicklItemListener {
    fun onClickMessage(item: MessageItemModel)

}