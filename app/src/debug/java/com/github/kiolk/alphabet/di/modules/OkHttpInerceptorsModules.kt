package com.github.kiolk.alphabet.di.modules

import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.github.kiolk.alphabet.di.qualifaiers.OkHttpInterceptors
import com.github.kiolk.alphabet.di.qualifaiers.OkHttpNetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class OkHttpInerceptorsModules : BaseInterceptorModule() {

    @Singleton
    @IntoSet
    @OkHttpInterceptors
    @Provides
    fun provideHttpInterceptor() : Interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @IntoSet
    @OkHttpNetworkInterceptor
    @Provides
    fun provideHtttpNetworkInterceptor() : Interceptor = StethoInterceptor()


}