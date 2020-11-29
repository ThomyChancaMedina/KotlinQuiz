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

    val data: ArrayList<Question> = ArrayList()

    sealed class UiModel {
        class Content(val question: List<Question>) : UiModel()
    }

    init {
        initScope()
        updateQuestion()
    }

    fun setQuestion(questions: List<Question>) {
        data.addAll(questions)

    }

    fun onGetAllQuestions() {
        launch {
            _question.value = UiModel.Content(getQuestions.invoke())
        }
    }

    private var currentIndex = 0

    private val topQuestion
        get() = data[currentIndex % data.size]

    private val bottomQuestion
        get() = data[(currentIndex + 1) % data.size]


    override fun onCleared() {
        destroyScope()
        super.onCleared()

    }

    private fun updateQuestion() {
        personList.value = TinderCard(topCard, bottomCard)
    }


}
