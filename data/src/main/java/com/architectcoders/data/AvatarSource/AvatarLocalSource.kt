package com.architectcoders.data.AvatarSource

import com.architectcoders.domain.avatar.Avatar

interface AvatarLocalSource {

    suspend fun isEmpty(): Boolean
    suspend fun saveAvatar(avatar: List<Avatar>)
    suspend fun getAvatar(): List<Avatar>
    suspend fun findByTitle(title: String): Avatar
    suspend fun updateAvatar(avatar: Avatar)

}