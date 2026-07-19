package com.razzaaq.moviedb.api.dto

data class NowPlayingUiState(
    val nowPlaying: List<Movie> = listOf<Movie>(),
    val topRated: List<Movie> = listOf(),
    val upComing: List<Movie> = listOf(),
    val popular: List<Movie> = listOf(),
    val posterImage: Image = Image(),
)