package com.timelysoft.tsjdomcom.utils


import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import java.util.regex.Pattern


object MyUtils {
    fun toMyDate(date: String): String {
        return try {
            date.substring(8, 10) + "." + date.substring(5, 7) + "." + date.substring(0, 4)
        } catch (e: Exception) {
            ""
        }

    }

    fun toMyDateTime(date: String): String {
        return try {
            date.substring(8, 10) + "." + date.substring(5, 7) + "." + date.substring(
                0,
                4
            ) + " " + date.substring(11, 16)
        } catch (e: Exception) {
            ""
        }
    }

    fun toServerDate(date: String): String {
        return date.substring(6) + "-" + date.substring(3, 5) + "-" + date.substring(
            0, 2
        ) + "T00:00:00+06:00"
    }

    fun convertDateServer( year: Int , month: Int, day: Int): String {
        var date = ""

        date += if (year < 10) {
            "0" + year
        } else {
            year.toString()
        }
        date += "."

        date += if (month <= 7) {
            "0" + month + "."

        } else {
            month.toString() + "."
        }

        date += if(day < 10){
            "0" + day
        }else{
            day.toString()
        }
        return date
    }

    fun convertDate(day: Int, month: Int, year: Int): String {
        var date = ""

        date += if (day < 10) {
            "0" + day
        } else {
            day.toString()
        }
        date += "."


        date += if (month < 10) {
            "0" + month

        } else {
            month.toString()
        }
        date += "." + year
        return date
    }

    fun dateConverting(text: String): Triple<Int, Int, Int> {
        val day = text.substring(8, 10)
        val month = text.substring(5, 7)
        val year = text.substring(0, 4)
        return Triple(day.toInt(), month.toInt(), year.toInt())
    }

    fun hideKeyboard(activity: Activity, view: View) {
        // ???????????????? ????????????????????
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun emailValidate(text: String): Boolean {
        val regExpn =
            ("""^(([\w-]+\.)+[\w-]+|([a-zA-Z]{1}|[\w-]{2,}))@((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|([a-zA-Z]+[\w-]+\.)+[a-zA-Z]{2,4})$""")
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }


    fun isImage(fileName: String): Boolean {
        val regExpn = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)"
        val pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(fileName)
        return matcher.matches()
    }


    fun fileName(url: String): String {
        return try {
            url.substring(url.lastIndexOf("/") + 1)
        } catch (e: Exception) {
            url
        }
    }

    fun copyText(text: String, context : Context) {
        var clipboard = getSystemService(context, ClipboardManager::class.java)
        var clip = ClipData.newPlainText("label", text)
        clipboard!!.setPrimaryClip(clip)
    }
}
