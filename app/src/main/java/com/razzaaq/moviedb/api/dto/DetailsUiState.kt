package com.razzaaq.moviedb.api.dto

data class DetailsUiState(
    val movieDetail: MovieDetailUi = MovieDetailUi(),
    val posterImage: Image = Image()
)