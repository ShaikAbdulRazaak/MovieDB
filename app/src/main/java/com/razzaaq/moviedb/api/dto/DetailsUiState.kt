package com.razzaaq.moviedb.api.dto

data class DetailsUiState(
    val movieDetail: MovieDetail = MovieDetail(),
    val posterImage: Image = Image()
)