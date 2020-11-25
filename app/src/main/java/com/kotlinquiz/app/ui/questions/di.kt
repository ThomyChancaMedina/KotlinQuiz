package com.kotlinquiz.app.ui.questions

import com.architectcoders.data.repository.QuestionRepository
import com.architectcoders.usecases.GetQuestions
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class QuestionModule{

    @Provides
    fun questionViewModelProvider(getQuestions: GetQuestions)=QuestionViewModel(getQuestions)

    @Provides
    fun getQuestionProvider(questionRepository: QuestionRepository)=GetQuestions(questionRepository)

}
@Subcomponent(modules = [(QuestionModule::class)])
interface QuestionComponent{
    val questionViewModel:QuestionViewModel
}