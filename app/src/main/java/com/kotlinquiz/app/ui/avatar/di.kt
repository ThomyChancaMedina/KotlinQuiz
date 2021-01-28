package com.kotlinquiz.app.ui.avatar

import com.architectcoders.data.repository.AvatarRepository
import com.architectcoders.usecases.FindAvatar
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class AvatarActivityModule(private val title: String) {

    @Provides
    fun avatarViewModel(
        findAvatar: FindAvatar,

        ): AvatarViewModel {
        return AvatarViewModel(title, findAvatar)
    }

    @Provides
    fun findAvatarByTitleProvider(avatarRepository: AvatarRepository) = FindAvatar(avatarRepository)


}

@Subcomponent(modules = [(AvatarActivityModule::class)])
interface AvatarActivityComponent {
    val avatarViewModel: AvatarViewModel
}