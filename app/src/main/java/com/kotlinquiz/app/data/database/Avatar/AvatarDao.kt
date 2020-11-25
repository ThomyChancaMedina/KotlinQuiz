package com.kotlinquiz.app.data.database.Avatar

import androidx.room.*

@Dao
interface AvatarDao {
    @Query("SELECT * FROM Avatar")
    fun getAllAvatar(): List<Avatar>

    @Query("SELECT * FROM Avatar WHERE title=:title")
    fun findByTitle(title:String):Avatar

    @Query("SELECT COUNT(title) FROM Avatar")
    fun avatarCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertTitle(title: List<Avatar>)


    @Update
    fun updateTitle(title: Avatar)
}