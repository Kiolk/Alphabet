package com.github.kiolk.common.domain.base

interface UseCase<T, Params> {

    fun execute(params : Params) : T
}