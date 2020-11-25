package com.architectcoders.data.source

import com.architectcoders.domain.question.Question

interface LocalDataSource {

    suspend fun isEmpty():Boolean
    suspend fun saveQuestion(question:List<Question>)
    suspend fun getQuestions():List<Question>
    suspend fun findById(id: String): Question
    suspend fun update(question: Question)
}