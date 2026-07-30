package com.razzaaq.moviedb.data.api.interceptor

import com.razzaaq.moviedb.data.api.ApiKeyProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val apiKeyProvider: ApiKeyProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${apiKeyProvider.apiKey()}")
                .build()
        )
}