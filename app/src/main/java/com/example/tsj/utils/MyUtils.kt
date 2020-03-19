package com.example.tsj.utils
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
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
        return date.substring(6) + "-" + date.substring(3, 5) + "-" + date.substring(
            0,
            2
        ) + "T00:00:00+06:00"

    }

    fun convertDate(day: Int, month: Int, year: Int): String {
        var date = ""

        date += if (day < 10) {
            "0" + day
        } else {
            day.toString()
        }
        date += "."


        date += if (month< 10) {
            "0" + month

        } else {
            month.toString()
        }
        date += "." + year
        return date
    }

    fun dateConverting(text: String): Triple<Int, Int, Int> {
        var day = text.substring(8,10)
        var month = text.substring(5,7)
        var year = text.substring(0,4)
        return Triple(day.toInt(), month.toInt(), year.toInt())
    }

    fun hideKeyboard(activity: Activity,view: View) {
        // скрывает клавиатуру
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
