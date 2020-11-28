package com.kotlinquiz.app.data.database.test

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TestQuestion(
    @PrimaryKey(autoGenerate = true)
    val uniqueId:Int,
    val text:String,
    val pos:Int,
    val answer:String,
)