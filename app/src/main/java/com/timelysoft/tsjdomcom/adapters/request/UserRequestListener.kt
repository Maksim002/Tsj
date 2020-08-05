package com.timelysoft.tsjdomcom.adapters.request

interface UserRequestListener {
    fun userRequestClick(item: Int)

    fun userRequestClickOwner(id: Int, position: Int)
}