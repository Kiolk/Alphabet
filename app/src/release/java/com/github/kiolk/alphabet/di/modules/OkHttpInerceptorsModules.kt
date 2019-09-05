package com.github.kiolk.alphabet.di.modules

import com.github.kiolk.alphabet.di.qualifaiers.OkHttpInterceptors
import com.github.kiolk.alphabet.di.qualifaiers.OkHttpNetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
class OkHttpInerceptorsModules : BaseInterceptorModule() {

    @Singleton
    @IntoSet
    @OkHttpInterceptors
    @Provides
    fun provideHttpInterceptor() : Interceptor = Interceptor { chain -> chain.proceed(chain.request()) }

    @Singleton
    @IntoSet
    @OkHttpNetworkInterceptor
    @Provides
    fun provideHtttpNetworkInterceptor() : Interceptor = Interceptor { chain -> chain.proceed(chain.request()) }

}