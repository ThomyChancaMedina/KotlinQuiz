package com.architectcoders.usecases

import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.domain.question.Question

class GetQuestions(private val questionRepository: QuestionRepository) {

    suspend fun invoke(): List<Question> = questionRepository.getQuestions()

}