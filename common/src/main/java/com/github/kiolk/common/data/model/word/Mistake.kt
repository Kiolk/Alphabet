package com.github.kiolk.common.data.model.word

import com.google.gson.annotations.SerializedName

data class Mistake(@SerializedName("word")
                   private val word: String,

                   @SerializedName("description")
                   private val description: String)