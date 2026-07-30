package com.razzaaq.moviedb.ui.model

data class UiState(
    val nowPlaying: List<MovieUi> = listOf(),
    val topRated: List<MovieUi> = listOf(),
    val upComing: List<MovieUi> = listOf(),
    val popular: List<MovieUi> = listOf()
)