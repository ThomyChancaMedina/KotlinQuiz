package com.kotlinquiz.app.data.server.provider

import com.architectcoders.data.testSource.TestDataSource
import com.architectcoders.domain.test.TestQuestion

class TestDbDataSource : TestDataSource {

    override suspend fun getAllTest(): List<TestQuestion> =
        getItemsForProvider()
            .map { it.toDomainTest() }

}