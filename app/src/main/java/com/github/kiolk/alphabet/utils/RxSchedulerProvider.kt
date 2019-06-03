package com.github.kiolk.alphabet.utils

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RxSchedulerProvider {

    protected val mainScheduler: Scheduler by lazy { AndroidSchedulers.mainThread() }

    protected val schedulerIo: Scheduler by lazy { Schedulers.io() }

    protected val computationSchedulers: Scheduler by lazy { Schedulers.computation() }

    fun goIoToMainTransformerComplitable(): CompletableTransformer = CompletableTransformer {
        it
                .subscribeOn(schedulerIo)
                .observeOn(mainScheduler)
    }

    fun <T> goIoToMainTransformerFloweable() = FlowableTransformer<T, T> {
        it
                .subscribeOn(schedulerIo)
                .observeOn(mainScheduler)
    }
}