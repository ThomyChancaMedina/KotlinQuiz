package com.architectcoders.domain.question

data class Question(
    val id: String,
    val question: String,
    val answers: List<Answer>
)