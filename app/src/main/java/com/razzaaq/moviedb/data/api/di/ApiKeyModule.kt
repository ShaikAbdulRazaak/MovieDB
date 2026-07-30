package com.razzaaq.moviedb.data.api.di

import com.razzaaq.moviedb.data.api.ApiKeyProvider
import com.razzaaq.moviedb.data.api.ApiKeyProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiKeyModule {
    @Binds
    @Singleton
    abstract fun bindApiKeyProvider(
        apiKeyProviderImpl: ApiKeyProviderImpl
    ): ApiKeyProvider
}
