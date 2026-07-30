package com.razzaaq.moviedb.api.interceptor

import com.razzaaq.moviedb.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        chain.proceed(
            request = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.apiKey}")
                .build()
        )
}