package com.github.kiolk.alphabet.utils

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class RxSchedulerProvider {

    protected val mainScheduler: Scheduler by lazy { AndroidSchedulers.mainThread() }

    protected val schedulerIo: Scheduler by lazy { Schedulers.from(Executors.newFixedThreadPool(3)) }

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

    fun <T> getIoToMainTransformerSingle(): SingleTransformer<T, T> = SingleTransformer<T, T> {
        it
                .subscribeOn(schedulerIo)
                .observeOn(mainScheduler)
    }
}