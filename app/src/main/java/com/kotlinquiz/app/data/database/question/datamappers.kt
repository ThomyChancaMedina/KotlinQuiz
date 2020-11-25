package com.kotlinquiz.app.data.database.question

import com.architectcoders.domain.question.Question
import com.kotlinquiz.app.data.database.question.QuestionsAnswers as DataBase

fun Question.toRoomQuestion(): DataBase =
    DataBase(
        id,
        question,
        answers
    )

fun DataBase.toDomainQuestion(): Question =
    Question(
        id,
        question,
        answer
    )



