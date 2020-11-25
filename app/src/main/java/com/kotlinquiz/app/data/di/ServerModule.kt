package com.kotlinquiz.app.data.di


import com.kotlinquiz.app.data.server.TheQuestionDb
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServerModule {

    @Singleton
    @Provides
    @Named("baseUrl")
    fun baseUrlProvider()="https://kotlinquiz-6d189.firebaseio.com/"

    @Singleton
    @Provides
    fun questionDBProvider(@Named("baseUrl")baseUrl:String)= TheQuestionDb(baseUrl)
}