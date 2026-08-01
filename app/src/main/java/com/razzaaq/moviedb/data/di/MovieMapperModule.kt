package com.razzaaq.moviedb.data.di

import com.razzaaq.moviedb.data.mapper.MovieMappers
import com.razzaaq.moviedb.util.StringProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MovieMapperModule {
    @Provides
    fun provideMovieMapper(stringProvider: StringProvider) = MovieMappers(stringProvider)
}