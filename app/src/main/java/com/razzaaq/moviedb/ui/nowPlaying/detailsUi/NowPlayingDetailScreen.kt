package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.MovieDetail

@Composable
fun NowPlayingDetailScreen(
    modifier: Modifier = Modifier,
    posterImage: Image,
    movieDetail: MovieDetail
) {
    Column {
        MoviePoster(
            title = movieDetail.title,
            backdropPath = movieDetail.backdropPath,
            tagline = movieDetail.tagline,
            posterImage = posterImage,
            modifier = modifier
        )
        MovieDetailItems(modifier, movieDetail, posterImage)
    }
}