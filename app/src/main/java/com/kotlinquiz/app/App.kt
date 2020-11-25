package com.kotlinquiz.app

import android.app.Application
import com.kotlinquiz.app.data.di.DaggerProjectComponent
import com.kotlinquiz.app.data.di.ProjectComponent
import com.kotlinquiz.app.data.storage.AvatarManager
import com.kotlinquiz.app.data.storage.SharedPreferencesStorage


open class App : Application() {

    lateinit var component: ProjectComponent
        private set

    val avatar by lazy {
        AvatarManager(SharedPreferencesStorage(this))
    }

    override fun onCreate() {
        super.onCreate()

        component = initTestComponent()

    }

    open fun initTestComponent() = DaggerProjectComponent.factory().create(this)
}