package com.architectcoders.data.source

import com.architectcoders.domain.avatar.Avatar
import com.architectcoders.domain.question.Question


interface RemoteDataSource {
    suspend fun getAllQuestions():List<Question>
    suspend fun getAllAvatar(): List<Avatar>

}