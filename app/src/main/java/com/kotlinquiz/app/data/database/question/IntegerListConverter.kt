package com.kotlinquiz.app.data.database.question

import androidx.room.TypeConverter
import com.architectcoders.domain.question.Answer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IntegerListConverter {
    @TypeConverter
    fun fromString(value: String): List<Answer>? {
        val listType = object : TypeToken<List<Answer>>() {}.type
        return Gson().fromJson<List<Answer>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Answer>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}