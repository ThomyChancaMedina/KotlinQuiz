package com.architectcoders.usecases

import com.architectcoders.data.repository.AvatarRepository
import com.architectcoders.domain.avatar.Avatar

class GetAvatars(private val avatarRepository: AvatarRepository) {
    suspend fun invoke(): List<Avatar> = avatarRepository.getAvatars()
}