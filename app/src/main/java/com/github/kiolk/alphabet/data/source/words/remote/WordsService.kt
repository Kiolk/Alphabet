package com.github.kiolk.alphabet.data.source.words.remote

import com.github.kiolk.alphabet.data.models.word.Mistake
import com.github.kiolk.alphabet.data.models.word.RemoteWord
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WordsService {

    @GET("/words")
    fun getWords() : Flowable<List<RemoteWord>>

    @POST("/mistake")
    fun sendMistake(@Body mistake: Mistake): Completable
}