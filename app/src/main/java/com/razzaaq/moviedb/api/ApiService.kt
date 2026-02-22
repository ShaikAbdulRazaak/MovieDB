package com.razzaaq.moviedb.api

import com.razzaaq.moviedb.api.dto.ConfigurationDetail
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.api.dto.NowPlayingDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlayingDto

    @GET("configuration")
    suspend fun getTMDBConfiguration(): ConfigurationDetail

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId: Int): MovieDetail
}