package com.kotlinquiz.app.data.storage

import android.content.Context

class SharedPreferencesStorage(context: Context) : Storage {

    private val sharedPreferences = context.getSharedPreferences("image", Context.MODE_PRIVATE)

    override fun setString(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    override fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }
}