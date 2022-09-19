package com.suraksha.cloud

import android.content.Context
import com.google.gson.GsonBuilder
import com.suraksha.cloud.services.LabsApiService
import com.suraksha.cloud.services.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BASE_URL = "https://qa.healthbus.in/"

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    private val READ_TIMEOUT = 60
    private val WRITE_TIMEOUT = 60
    private val CONNECTION_TIMEOUT = 60
    private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L // 10 MB

    @Provides
    fun provideOkHttpClient(
        headerInterceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)
        okHttpClientBuilder.addInterceptor(headerInterceptor)
        okHttpClientBuilder.addInterceptor(
            AuthInterceptor(
                CloudConnector.connectorInterface?.getToken() ?: "",
                CloudConnector.connectorInterface?.getAppId() ?: 0
            )
        )
        okHttpClientBuilder.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        return okHttpClientBuilder.build()
    }


    @Provides
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
            //hear you can add all headers you want by calling 'requestBuilder.addHeader(name ,  value)'
            it.proceed(requestBuilder.build())
        }
    }


    @Provides
    internal fun provideCache(@ApplicationContext context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }


    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    fun provideLabsApiService(retrofit: Retrofit): LabsApiService =
        retrofit.create(LabsApiService::class.java)

}