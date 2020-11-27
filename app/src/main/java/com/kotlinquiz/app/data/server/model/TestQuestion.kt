package com.kotlinquiz.app.data.server.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestQuestion(
    val uniqueId: Int,
    val text: String,
    val pos: Int,
    val answer: String,
) : Parcelable