package com.kotlinquiz.app.data.storage


private const val SAVE_URL_IMG = "save_url_image"


class AvatarManager(private val storage: Storage) {

    val avatarImage: String
        get() = storage.getString(SAVE_URL_IMG)

    fun saveImage(image: String) {
        storage.setString(SAVE_URL_IMG, image)
    }

}