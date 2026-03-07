package com.razzaaq.moviedb.api.dto

data class NowPlayingUiState(
    val nowPlaying: List<Movie> = listOf<Movie>(),
    val posterImage: Image = Image(),
)