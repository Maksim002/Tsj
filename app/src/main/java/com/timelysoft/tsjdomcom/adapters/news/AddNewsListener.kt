package com.timelysoft.tsjdomcom.adapters.news

interface AddNewsListener {
    fun addNewsClick(position: Int, fileName: String = "")
}