package com.github.kiolk.alphabet.data.source.words.remote

import com.github.kiolk.alphabet.data.models.word.RemoteWord
import io.reactivex.Flowable
import retrofit2.http.GET

interface WordsService {

    @GET("/words")
    fun getWords() : Flowable<List<RemoteWord>>
}