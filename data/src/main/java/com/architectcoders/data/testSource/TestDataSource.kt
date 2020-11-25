package com.architectcoders.data.testSource

import com.architectcoders.domain.test.TestQuestion

interface TestDataSource {
    suspend fun getAllTest(): List<TestQuestion>

}