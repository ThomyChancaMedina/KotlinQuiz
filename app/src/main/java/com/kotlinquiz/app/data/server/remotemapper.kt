package com.kotlinquiz.app.data.server

import com.kotlinquiz.app.data.server.model.Questions as ServerQuestion
import com.architectcoders.domain.question.Question

import com.architectcoders.domain.question.Answer
import com.kotlinquiz.app.data.server.model.Answer as ServerAnswer

fun ServerQuestion.toDomainQuestionAnswer(): Question =
    Question(
       id =  id,
       question =  question,
       answers =  answers.map { it.toDomainAnswer() }
    )

fun ServerAnswer.toDomainAnswer(): Answer =
    Answer(
        answer,
        isCorrect
    )