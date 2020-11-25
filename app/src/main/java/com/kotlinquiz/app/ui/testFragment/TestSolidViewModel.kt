package com.kotlinquiz.app.ui.testFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.architectcoders.domain.test.TestQuestion
import com.kotlinquiz.app.ui.common.Event
import com.kotlinquiz.app.ui.common.ScopedViewModel
import com.kotlinquiz.app.ui.common.notifyObserver
import com.architectcoders.usecases.GetTestQuestion
import kotlinx.coroutines.launch

class TestSolidViewModel(
    private val getTestQuestion: GetTestQuestion
) : ScopedViewModel() {

    private val _questions = MutableLiveData<List<TestQuestion>>()
    val questions: LiveData<List<TestQuestion>>
        get() = _questions

    private val _model = MutableLiveData<Event<Unit>>()
    val model: LiveData<Event<Unit>>
        get() = _model


    private val _modelTet = MutableLiveData<UiModel>()
    val modelTest: LiveData<UiModel>
        get() = _modelTet

    sealed class UiModel {
        object Loading : UiModel()
        data class Content(val question: List<TestQuestion>) : UiModel()

    }


    init {
        initScope()
        refresh()
    }

    fun getQuestionFromDb() {
        launch {
            _questions.value = getTestQuestion.invoke()
        }
    }

    fun updateAnswer(position: Int, selection: String) {
        val question = questions.value?.get(position) ?: return

        question.answer = selection
        _questions.notifyObserver()

    }

    private fun refresh() {
        _model.value = Event(Unit)
    }

    fun calculateResult(): List<String> {
        var result: List<String> = listOf()

        questions.value?.forEach { question ->
            result = result + question.answer

        }

        return result
    }


    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}