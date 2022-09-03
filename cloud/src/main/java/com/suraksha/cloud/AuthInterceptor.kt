package com.suraksha.cloud

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor (private val token: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(newRequest)


        /*val original = chain.request()
        val httpUrl = original.url.newBuilder()
            .addQueryParameter("Authorization", "Bearer " + token)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(httpUrl)

        return chain.proceed(requestBuilder.build())*/
    }
}