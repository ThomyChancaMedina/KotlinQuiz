package com.kotlinquiz.app.data.database.avatar

import com.architectcoders.data.AvatarSource.AvatarLocalSource
import com.architectcoders.domain.avatar.Avatar
import com.kotlinquiz.app.data.database.ProjectDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomAvatarDataSource(db: ProjectDatabase) : AvatarLocalSource {

    private val avatarDao = db.avatarDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { avatarDao.avatarCount() <= 0 }

    override suspend fun saveAvatar(avatar: List<Avatar>) {
        withContext(Dispatchers.IO) { avatarDao.insertTitle(avatar.map { it.toRoomAvatar() }) }
    }

    override suspend fun getAvatar(): List<Avatar> =
        withContext(Dispatchers.IO) {
            avatarDao.getAllAvatar().map { it.toDomainAvatar() }
        }

    override suspend fun findByTitle(title: String): Avatar = withContext(Dispatchers.IO){
        avatarDao.findByTitle(title).toDomainAvatar()
    }

    override suspend fun updateAvatar(avatar: Avatar) {
        withContext(Dispatchers.IO) { avatarDao.updateTitle(avatar.toRoomAvatar()) }
    }

}