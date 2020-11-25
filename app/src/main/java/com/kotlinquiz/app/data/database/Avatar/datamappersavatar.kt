package com.kotlinquiz.app.data.database.Avatar


import com.architectcoders.domain.avatar.Avatar
import com.kotlinquiz.app.data.database.Avatar.Avatar as DomainAvatar
import com.kotlinquiz.app.data.server.model.Avatar as ServerAvatar

fun Avatar.toRoomAvatar():DomainAvatar =
    DomainAvatar(
        image,
        title
    )



fun DomainAvatar.toDomainAvatar():Avatar=
    Avatar(
        image=image,
        title= title,
    )
fun ServerAvatar.toDomainAvatar():Avatar=
    Avatar(
        image,
        title,
    )