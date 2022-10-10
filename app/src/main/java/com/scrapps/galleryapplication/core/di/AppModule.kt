package com.scrapps.galleryapplication.core.di

import com.scrapps.galleryapplication.BuildConfig
import com.scrapps.galleryapplication.data.remote.UnsplashApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val CONTENT_TYPE = "Content-Type"
    private const val APPLICATION_JSON = "application/json"
    private const val ACCEPT_VERSION = "Accept-Version"

    @Provides
    @Singleton
    fun provideStockApi(): UnsplashApi{
        val interceptor = HttpLoggingInterceptor()
        //setup logging network request
        if(BuildConfig.DEBUG) interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .addHeader(ACCEPT_VERSION, "v1")
                .build()
            chain.proceed(request)
        }
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(UnsplashApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create()
    }
}