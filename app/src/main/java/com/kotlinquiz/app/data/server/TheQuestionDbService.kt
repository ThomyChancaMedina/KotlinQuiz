package com.kotlinquiz.app.data.server


import com.kotlinquiz.app.data.server.model.Avatar
import com.kotlinquiz.app.data.server.model.Questions
import retrofit2.http.GET

interface TheQuestionDbService {
    @GET("Questions/Jwf0Y4VAFsX0m8hBRP6f/questions.json")
    suspend fun listQuestionAsync(): List<Questions>

    @GET("avatars.json")
    suspend fun listAvatar(): List<Avatar>
}