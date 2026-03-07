package com.razzaaq.moviedb.api.dto

data class NowPlayingDetailsUiState(
    val nowPlayingDetails: MovieDetail = MovieDetail(),
    val posterImage: Image = Image()
)