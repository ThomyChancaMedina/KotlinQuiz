package com.architectcoders.domain.test

data class TestQuestion(
    val uniqueId:Int,
    val text:String,
    val pos:Int,
    var answer:String,
)