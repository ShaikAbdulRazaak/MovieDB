package com.razzaaq.moviedb.data.api

import com.razzaaq.moviedb.BuildConfig
import javax.inject.Inject


class ApiKeyProviderImpl @Inject constructor() : ApiKeyProvider {
    override fun apiKey(): String = BuildConfig.apiKey
}

