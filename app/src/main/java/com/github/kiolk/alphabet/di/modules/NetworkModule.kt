package com.github.kiolk.alphabet.di.modules

import android.content.Context
import com.github.kiolk.alphabet.BuildConfig
import com.github.kiolk.alphabet.di.qualifaiers.OkHttpInterceptors
import com.github.kiolk.alphabet.di.qualifaiers.OkHttpNetworkInterceptor
import com.github.kiolk.common.data.holders.AppInfoComponentHolder
import com.github.kiolk.common.data.model.appInfo.AppInfo
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [OkHttpInerceptorsModules::class, NetworkServicesModule::class])
class NetworkModule {

    @Named(API_URL)
    @Singleton
    @Provides
    fun provideApiUrl() : String = BuildConfig.ApiUrl

    @Singleton
    @Provides
    fun provideGson() : Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideGsconConverter(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Singleton
    @Provides
    fun provideOkHttpCache(context: Context): Cache = Cache(context.cacheDir, 10 * 1024 * 1024L)

    @Singleton
    @Provides
    fun provideCallAddapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    fun provideOkHttp(cache: Cache,
                      @OkHttpInterceptors interceptors: Set<@JvmSuppressWildcards Interceptor>,
                      @OkHttpNetworkInterceptor networkInterceptors: Set<@JvmSuppressWildcards Interceptor>) : OkHttpClient {

        val builder = OkHttpClient.Builder()

        for (interceptor in interceptors){
            builder.addInterceptor(interceptor)
        }

        for(networkInterceptor in networkInterceptors){
            builder.addNetworkInterceptor(networkInterceptor)
        }

        return builder
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .cache(cache)
                .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named(API_URL) baseApi: String,
                        converterFactory: Converter.Factory,
                        okhhtpClient: OkHttpClient,
                        callAdapterFactory: CallAdapter.Factory): Retrofit{
        val builder = Retrofit.Builder()

        return builder
            .baseUrl(baseApi)
            .client(okhhtpClient)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun appInfo(): AppInfo {
        return AppInfoComponentHolder.get().getAppInfo()
    }

    companion object {
        private const val API_URL = "API_URL"
    }
}