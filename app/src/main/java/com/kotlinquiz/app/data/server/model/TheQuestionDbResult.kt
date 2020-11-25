package com.kotlinquiz.app.data.server.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Questions(
    val id: String,
    val question: String,
    val answers: List<Answer>
): Parcelable

@Parcelize
data class Answer(
    val answer: String,
    val isCorrect: Boolean
) : Parcelable









