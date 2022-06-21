package com.github.kiolk.common.domain.repository.word.source.remote

import com.github.kiolk.common.data.model.word.Mistake
import com.github.kiolk.common.data.model.word.RemoteWord
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