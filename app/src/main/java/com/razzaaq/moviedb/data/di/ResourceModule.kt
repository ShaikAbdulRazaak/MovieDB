package com.razzaaq.moviedb.data.di

import com.razzaaq.moviedb.util.StringProvider
import com.razzaaq.moviedb.util.StringProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceModule {
    @Binds
    @Singleton
    abstract fun provideStringResources(stringProviderImpl: StringProviderImpl): StringProvider
}