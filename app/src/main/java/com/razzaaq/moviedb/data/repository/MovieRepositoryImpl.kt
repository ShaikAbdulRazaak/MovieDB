package com.razzaaq.moviedb.data.repository

import com.razzaaq.moviedb.data.api.ApiService
import com.razzaaq.moviedb.data.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.dto.MovieDetail
import com.razzaaq.moviedb.data.dto.MovieResponseDto
import com.razzaaq.moviedb.util.NetworkResult
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieRepository {
    override suspend fun getNowPlayingMovies(): NetworkResult<MovieResponseDto> = safeApiCall {
        apiService.getNowPlayingMovies()
    }

    override suspend fun getPopular(): NetworkResult<MovieResponseDto> = safeApiCall {
        apiService.getPopular()
    }

    override suspend fun getTopRated(): NetworkResult<MovieResponseDto> = safeApiCall {
        apiService.getTopRated()
    }

    override suspend fun getUpcomingMovies(): NetworkResult<MovieResponseDto> = safeApiCall {
        apiService.getUpcomingMovies()
    }

    override suspend fun getTMDBConfiguration(): NetworkResult<ConfigurationDetail> = safeApiCall {
        apiService.getTMDBConfiguration()
    }

    override suspend fun getMovieDetail(movieId: Int): NetworkResult<MovieDetail> = safeApiCall {
        apiService.getMovieDetail(movieId)
    }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
        return try {
            NetworkResult.Success(apiCall())
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }
}
