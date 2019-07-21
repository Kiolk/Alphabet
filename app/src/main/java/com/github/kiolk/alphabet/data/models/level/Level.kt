package com.github.kiolk.alphabet.data.models.level

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Level(val image: Int,
                 val title: String): Parcelable