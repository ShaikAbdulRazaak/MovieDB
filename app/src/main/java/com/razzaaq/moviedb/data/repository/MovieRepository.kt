package com.razzaaq.moviedb.data.repository

import com.razzaaq.moviedb.data.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.dto.MovieDetail
import com.razzaaq.moviedb.data.dto.MovieResponseDto
import com.razzaaq.moviedb.util.NetworkResult

interface MovieRepository {
    suspend fun getNowPlayingMovies(): NetworkResult<MovieResponseDto>
    suspend fun getPopular(): NetworkResult<MovieResponseDto>
    suspend fun getTopRated(): NetworkResult<MovieResponseDto>
    suspend fun getUpcomingMovies(): NetworkResult<MovieResponseDto>
    suspend fun getTMDBConfiguration(): NetworkResult<ConfigurationDetail>
    suspend fun getMovieDetail(movieId: Int): NetworkResult<MovieDetail>
}
