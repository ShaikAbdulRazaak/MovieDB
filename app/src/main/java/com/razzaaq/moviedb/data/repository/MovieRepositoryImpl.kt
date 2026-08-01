package com.razzaaq.moviedb.data.repository

import com.razzaaq.moviedb.data.api.ApiService
import com.razzaaq.moviedb.data.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.dto.MovieDetail
import com.razzaaq.moviedb.data.dto.MovieResponseDto
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun getNowPlayingMovies(): MovieResponseDto = apiService.getNowPlayingMovies()
    override suspend fun getPopular(): MovieResponseDto = apiService.getPopular()
    override suspend fun getTopRated(): MovieResponseDto = apiService.getTopRated()
    override suspend fun getUpcomingMovies(): MovieResponseDto = apiService.getUpcomingMovies()
    override suspend fun getTMDBConfiguration(): ConfigurationDetail = apiService.getTMDBConfiguration()
    override suspend fun getMovieDetail(movieId: Int): MovieDetail = apiService.getMovieDetail(movieId)
}
