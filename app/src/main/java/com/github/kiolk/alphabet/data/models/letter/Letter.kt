package com.github.kiolk.alphabet.data.models.letter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Letter(val letter : String,
                  val letterValue : String,
                  val image : String,
                  var completedLevel: Float = -1.0f) : Parcelable