package com.architectcoders.data.repository

import com.architectcoders.data.AvatarSource.AvatarLocalSource
import com.architectcoders.data.source.RemoteDataSource
import com.architectcoders.domain.avatar.Avatar

class AvatarRepository (
    private val avatarLocalSource: AvatarLocalSource,
    private val avatarDataSource: RemoteDataSource
){
    suspend fun getAvatars():List<Avatar>{
        if(avatarLocalSource.isEmpty()){
            val avatars=avatarDataSource.getAllAvatar()
            avatarLocalSource.saveAvatar(avatars)
        }
        return avatarLocalSource.getAvatar()
    }

    suspend fun findByTitle(title:String):Avatar=avatarLocalSource.findByTitle(title)

    suspend fun updateAvatar(avatar:Avatar)=avatarLocalSource.updateAvatar(avatar)

}