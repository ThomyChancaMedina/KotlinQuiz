package com.kotlinquiz.app.data.database.question


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.architectcoders.domain.question.Answer


@Entity
data class QuestionsAnswers(
    @PrimaryKey val id: String,
    val question: String,
    val answer: List<Answer>
)

