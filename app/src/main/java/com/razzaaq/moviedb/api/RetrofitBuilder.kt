package com.razzaaq.moviedb.api

import com.razzaaq.moviedb.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

object RetrofitBuilder {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private fun converterFactory(): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())

    private fun request(chain: Interceptor.Chain): Request = chain.request()
        .newBuilder()
        .addHeader("Authorization", "Bearer ${BuildConfig.apiKey}")
        .build()

    private fun client(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            chain.proceed(
                request = request(chain)
            )
        }.build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client())
        .addConverterFactory(converterFactory())
        .build()
}