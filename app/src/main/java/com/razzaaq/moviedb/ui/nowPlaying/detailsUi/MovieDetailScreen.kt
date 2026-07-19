package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.MovieDetail

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    posterImage: Image,
    movieDetail: MovieDetail
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        item {
            MoviePoster(
                title = movieDetail.title,
                backdropPath = movieDetail.backdropPath.ifEmpty { movieDetail.posterPath },
                tagline = movieDetail.tagline,
                posterImage = posterImage
            )
        }
        item {
            MovieDetailItems(movieDetail = movieDetail, posterImage = posterImage)
        }
    }
}
