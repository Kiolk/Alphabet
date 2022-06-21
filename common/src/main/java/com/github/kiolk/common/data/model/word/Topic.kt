package com.github.kiolk.common.data.model.word

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Topic(val picture: String,
                 val title: String,
                 val read: Int,
                 val total: Int): Parcelable