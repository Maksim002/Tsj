package com.timelysoft.tsjdomcom.adapters.files


interface GeneralClickListener {

    fun onClickItem(position: Int, url: String = "", fileName: String = "")
}