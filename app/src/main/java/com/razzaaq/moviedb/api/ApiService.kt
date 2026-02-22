package com.razzaaq.moviedb.api

import com.razzaaq.moviedb.api.dto.NowPlayingDto
import com.razzaaq.moviedb.api.dto.TMDBConfiguration
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingDto

    @GET("configuration")
    suspend fun getTMDBConfiguration(): TMDBConfiguration
}