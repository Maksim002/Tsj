package com.timelysoft.tsjdomcom.utils

import java.lang.Exception

fun String.toMyDate():String{
    return try {
        this.substring(8, 10) + "." + this.substring(5, 7) + "." + this.substring(0, 4)
    } catch (e: Exception) {
        ""
    }
}