package com.kotlinquiz.app.data.database.avatar

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Avatar(
    @PrimaryKey val title:String,
    val image:String
)