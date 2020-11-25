package com.kotlinquiz.app.ui.testFragment

import com.architectcoders.data.repository.TestRepository
import com.architectcoders.usecases.GetTestQuestion
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class TestSolidModule{

    @Provides
    fun testViewModelProvider(getTestQuestion: GetTestQuestion)= TestSolidViewModel(getTestQuestion)

    @Provides
    fun getTestQuestionProvider(testRepository: TestRepository)=GetTestQuestion(testRepository)

}

@Subcomponent(modules = [(TestSolidModule::class)])
interface TestFragmentComponent{
    val testSolidViewModel:TestSolidViewModel
}