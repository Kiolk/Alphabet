package com.github.kiolk.alphabet.data.domain

interface UseCase<T, Params> {

    fun execute(params : Params) : T
}