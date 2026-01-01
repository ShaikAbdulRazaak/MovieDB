package com.razzaaq.moviedb.api

import com.razzaaq.moviedb.api.dto.NowPlaying
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): NowPlaying
}