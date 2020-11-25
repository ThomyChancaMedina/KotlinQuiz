package com.kotlinquiz.app.data.di

import android.app.Application
import com.kotlinquiz.app.ui.avatar.AvatarActivityComponent
import com.kotlinquiz.app.ui.avatar.AvatarActivityModule
import com.kotlinquiz.app.ui.splash.SplashComponent
import com.kotlinquiz.app.ui.splash.SplashModule
import com.kotlinquiz.app.ui.testFragment.TestFragmentComponent

import com.kotlinquiz.app.ui.testFragment.TestSolidModule
import com.kotlinquiz.app.ui.questions.QuestionComponent
import com.kotlinquiz.app.ui.questions.QuestionModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, DataModule::class, ServerModule::class])
interface ProjectComponent {
    fun plus(module: TestSolidModule): TestFragmentComponent
    fun plus(module: QuestionModule): QuestionComponent
    fun plus(module: SplashModule): SplashComponent
    fun plus(module:AvatarActivityModule):AvatarActivityComponent

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): ProjectComponent
    }

}