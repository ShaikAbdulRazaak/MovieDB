package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.razzaaq.moviedb.api.dto.TMDBConfiguration

@Composable
fun PosterImage(
    imagePath: String,
    configuration: TMDBConfiguration
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("${configuration.images.secureBaseUrl}${configuration.images.posterSizes.last()}$imagePath")
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}