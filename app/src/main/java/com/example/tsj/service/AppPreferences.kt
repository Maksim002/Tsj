package com.example.tsj.service

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val NAME = "TsjDom"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var id: Int
        get() = preferences.getInt("id", 0)
        set(value) = preferences.edit {
            it.putInt("id", value)
        }

}