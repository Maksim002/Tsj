package com.example.tsj.utils

import java.lang.Exception

object MyUtils {
    fun toMyDate(date: String): String {
        return try {
            date.substring(8, 10) + "." + date.substring(5, 7) + "." + date.substring(0, 4)
        } catch (e: Exception) {
            ""
        }


    }

    fun toServerDate(date: String): String {
        return date.substring(6)+"-"+date.substring(3,5)+"-"+date.substring(0,2)+"T00:00:00+06:00"

    }

}