package com.kotlinquiz.app.ui.avatar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.avatar.Avatar
import com.architectcoders.usecases.FindAvatar
import com.kotlinquiz.app.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class AvatarViewModel(
    private val title: String,
    private val findAvatar: FindAvatar
) : ScopedViewModel() {

    class UiAvatar(val avatar: Avatar)

    private val _avatar = MutableLiveData<UiAvatar>()
    val avatar: LiveData<UiAvatar>
        get() {
            if (_avatar.value == null) findAvatar()
            return _avatar
        }

    init {
        initScope()
    }

    private fun findAvatar() {
        launch {
            _avatar.value = UiAvatar(findAvatar.invoke(title))
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }

}