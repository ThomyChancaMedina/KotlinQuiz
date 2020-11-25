package com.kotlinquiz.app.data.server.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Avatar(
    val title: String,
    val image: String,
) : Parcelable