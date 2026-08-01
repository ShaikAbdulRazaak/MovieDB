package com.razzaaq.moviedb.data.repository

import com.razzaaq.moviedb.data.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.dto.MovieDetail
import com.razzaaq.moviedb.data.dto.MovieResponseDto

interface MovieRepository {
    suspend fun getNowPlayingMovies(): MovieResponseDto
    suspend fun getPopular(): MovieResponseDto
    suspend fun getTopRated(): MovieResponseDto
    suspend fun getUpcomingMovies(): MovieResponseDto
    suspend fun getTMDBConfiguration(): ConfigurationDetail
    suspend fun getMovieDetail(movieId: Int): MovieDetail
}
