package com.kotlinquiz.app.ui.splash

import com.architectcoders.data.repository.AvatarRepository
import com.architectcoders.usecases.GetAvatars
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class SplashModule {

    @Provides
    fun splashViewModuleProvider(getAvatars: GetAvatars) = SplashViewModel(getAvatars)

    @Provides
    fun getAvatarProvider(avatarRepository: AvatarRepository) = GetAvatars(avatarRepository)

}

@Subcomponent(modules = [(SplashModule::class)])
interface SplashComponent {
    val splashViewModel: SplashViewModel
}