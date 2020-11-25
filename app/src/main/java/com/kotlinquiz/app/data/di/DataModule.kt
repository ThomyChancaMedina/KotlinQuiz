package com.kotlinquiz.app.data.di

import com.architectcoders.data.AvatarSource.AvatarLocalSource
import com.architectcoders.data.repository.AvatarRepository
import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.data.repository.TestRepository
import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
import com.kotlinquiz.app.data.database.Avatar.RoomAvatarDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun testRepositoryProvider(
        testLocalSource: TestLocalSource,
        testDataSource: TestDataSource
    ) = TestRepository(testLocalSource, testDataSource)

    @Provides
    fun questionRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ) = QuestionRepository(localDataSource, remoteDataSource)

    @Provides
    fun avatarRepositoryProvider(
        avatarLocalSource: AvatarLocalSource,
        avatarDataSource: RemoteDataSource,
    )=AvatarRepository(avatarLocalSource,avatarDataSource)

}