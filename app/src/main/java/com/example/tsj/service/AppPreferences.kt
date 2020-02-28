package com.example.tsj.service

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.R.id.edit



@SuppressLint("StaticFieldLeak")
object AppPreferences {

    private const val NAME = "TsjDom"
    private const val MODE = Context.MODE_PRIVATE
    lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var token: String?
        get() = preferences.getString("token", "")
        set(value) = preferences.edit {
            it.putString("token", value)
        }

    var isLogined: Boolean
        get() = preferences.getBoolean("isLogined", false)
        set(value) = preferences.edit {
            it.putBoolean("isLogined", value)
        }

    var started: Boolean
            get() = preferences.getBoolean("started", false)
            set(e) = preferences.edit(){
                it.putBoolean("started", e)
            }
}