package com.kotlinquiz.app.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.avatar.Avatar
import com.architectcoders.usecases.GetAvatars
import com.kotlinquiz.app.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

class SplashViewModel(
    private val getAvatars: GetAvatars
) : ScopedViewModel() {


    private val _avatar = MutableLiveData<UiSplash>()
    val avatar: LiveData<UiSplash>
        get() {
            return _avatar
        }


    sealed class UiSplash {
        class Content(val avatars: List<Avatar>) : UiSplash()
        class Navigation(val avatar: Avatar) : UiSplash()
    }

    init {
        initScope()
    }

    fun onGetAllAvatars() {
        launch {
            _avatar.value = UiSplash.Content(getAvatars.invoke())
        }
    }

    fun onAvatarClicked(avatar: Avatar) {
        _avatar.value = UiSplash.Navigation(avatar)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }


}