package com.kotlinquiz.app.data.di

import android.app.Application
import androidx.room.Room
import com.architectcoders.data.AvatarSource.AvatarLocalSource
import com.architectcoders.data.source.LocalDataSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
import com.kotlinquiz.app.data.database.avatar.RoomAvatarDataSource
import com.kotlinquiz.app.data.database.ProjectDatabase
import com.kotlinquiz.app.data.database.test.RoomTestDataSource
import com.kotlinquiz.app.data.database.question.RoomDataSource
import com.kotlinquiz.app.data.server.QuestionDbDataSource
import com.kotlinquiz.app.data.server.TheQuestionDb
import com.kotlinquiz.app.data.server.provider.TestDbDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) = Room.databaseBuilder(
        app,
        ProjectDatabase::class.java,
        "question-db"
    ).build()

    @Provides
    fun localQuestionSourceProvider(db: ProjectDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun localAvatarSourceProvider(db: ProjectDatabase): AvatarLocalSource = RoomAvatarDataSource(db)

    @Provides
    fun remoteDataSourceProvider(theQuestionDb: TheQuestionDb): RemoteDataSource =
        QuestionDbDataSource(theQuestionDb)

    @Provides
    fun localTestSourceProvider(db: ProjectDatabase): TestLocalSource = RoomTestDataSource(db)

    @Provides
    fun testDataSourceProvider(): TestDataSource = TestDbDataSource()


}