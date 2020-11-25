package com.architectcoders.data.testSource

import com.architectcoders.domain.test.TestQuestion

interface TestLocalSource{

    suspend fun isEmpty():Boolean
    suspend fun saveTests(testQuestion:List<TestQuestion>)
    suspend fun getTests():List<TestQuestion>
    suspend fun findById(id: Int): TestQuestion
    suspend fun update(testQuestion: TestQuestion)
}