package com.architectcoders.data.repository

import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.domain.question.Question

class QuestionRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getQuestions():List<Question>{
        if (localDataSource.isEmpty()){
            val questionsAnswers=remoteDataSource.getAllQuestions()

            localDataSource.saveQuestion(questionsAnswers)
        }
        return localDataSource.getQuestions()
    }

    suspend fun findById(id:String): Question =localDataSource.findById(id)

    suspend fun update(question: Question)=localDataSource.update(question)

}