package com.kotlinquiz.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlinquiz.app.data.database.Avatar.Avatar
import com.kotlinquiz.app.data.database.Avatar.AvatarDao
import com.kotlinquiz.app.data.database.Test.TestDao
import com.kotlinquiz.app.data.database.Test.TestQuestion
import com.kotlinquiz.app.data.database.question.IntegerListConverter
import com.kotlinquiz.app.data.database.question.QuestionDao
import com.kotlinquiz.app.data.database.question.QuestionsAnswers

@Database(
    entities = [QuestionsAnswers::class, TestQuestion::class, Avatar::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(value = [IntegerListConverter::class])
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun testDao(): TestDao
    abstract fun avatarDao(): AvatarDao


}