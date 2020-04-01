package com.timelysoft.tsjdomcom.adapters.save

import com.timelysoft.tsjdomcom.service.model.SaveModel

interface SaveListener {
    fun onClick(save: SaveModel)
}