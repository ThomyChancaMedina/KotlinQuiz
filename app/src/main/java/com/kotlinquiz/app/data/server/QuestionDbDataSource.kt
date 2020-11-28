package com.kotlinquiz.app.data.server


import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.domain.avatar.Avatar
import com.architectcoders.domain.question.Question
import com.kotlinquiz.app.data.database.avatar.toDomainAvatar


class QuestionDbDataSource(private val theQuestionDb: TheQuestionDb) : RemoteDataSource {

    override suspend fun getAllQuestions(): List<Question> =
        theQuestionDb.retrofit
            .listQuestionAsync()
            .map { it.toDomainQuestionAnswer() }



    override suspend fun getAllAvatar(): List<Avatar> =
        theQuestionDb.retrofit
            .listAvatar()
            .map { it.toDomainAvatar() }

}