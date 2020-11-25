package com.architectcoders.usecases

import com.architectcoders.data.repository.TestRepository
import com.architectcoders.domain.test.TestQuestion

class GetTestQuestion(private val questionsRepository: TestRepository) {
    suspend fun invoke():List<TestQuestion> = questionsRepository.getTestQuestion()
}