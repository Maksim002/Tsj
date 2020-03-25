package com.example.tsj.adapters.save

import com.example.tsj.service.model.SaveModel

interface SaveListener {
    fun onClick(save: SaveModel)
}