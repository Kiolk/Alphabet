package com.github.kiolk.common.data.model.image

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Image(
    val url: String = "",
    val author: String = "",
    val isReviewed: Boolean = false,
    val wordId: String = "",
) : Parcelable