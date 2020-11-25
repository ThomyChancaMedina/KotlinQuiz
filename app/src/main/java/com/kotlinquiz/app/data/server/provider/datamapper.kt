package com.kotlinquiz.app.data.server.provider

import com.architectcoders.domain.test.TestQuestion
import com.kotlinquiz.app.data.database.Test.TestQuestion as DomainTest
import com.kotlinquiz.app.data.server.model.TestQuestion as ProviderTest

fun TestQuestion.toRoomTest(): DomainTest =
    DomainTest(
        uniqueId,
        text,
        pos,
        answer
    )


fun DomainTest.toDomainTest(): TestQuestion =
    TestQuestion(
        uniqueId,
        text,
        pos,
        answer,
    )

fun ProviderTest.toDomainTest(): TestQuestion =
    TestQuestion(
        0,
        text,
        pos,
        answer,
    )
