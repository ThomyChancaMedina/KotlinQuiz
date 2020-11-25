package com.architectcoders.data.repository

import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.data.testSource.TestLocalSource
import com.architectcoders.domain.test.TestQuestion

class TestRepository(

    private val testLocalSource:TestLocalSource,
    private val testDataSource: TestDataSource,
) {

    suspend fun getTestQuestion():List<TestQuestion>{
        if (testLocalSource.isEmpty()){
            val tests= testDataSource.getAllTest()
            testLocalSource.saveTests(tests)
        }
        return testLocalSource.getTests()
    }

}