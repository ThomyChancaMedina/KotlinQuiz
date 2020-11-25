package com.kotlinquiz.app.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.question.Question
import com.kotlinquiz.app.ui.common.ScopedViewModel
import kotlinx.coroutines.launch

import com.architectcoders.usecases.GetQuestions

class QuestionViewModel(private val getQuestions: GetQuestions) : ScopedViewModel() {


    private val _question = MutableLiveData<UiModel>()
    val question: LiveData<UiModel>
        get() {
            return _question
        }


    sealed class UiModel {
        class Content(val question: List<Question>) : UiModel()
    }

    init {
        initScope()
    }

    fun onGetAllQuestions() {
        launch {
            _question.value = UiModel.Content(getQuestions.invoke())
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()

    }


}
