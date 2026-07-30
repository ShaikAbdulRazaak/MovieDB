package com.razzaaq.moviedb.data.api

import com.razzaaq.moviedb.data.api.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.api.dto.MovieDetail
import com.razzaaq.moviedb.data.api.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponseDto

    @GET("movie/popular")
    suspend fun getPopular(): MovieResponseDto

    @GET("movie/top_rated")
    suspend fun getTopRated(): MovieResponseDto

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponseDto

    @GET("configuration")
    suspend fun getTMDBConfiguration(): ConfigurationDetail

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetail
}