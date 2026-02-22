package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun PosterImage(
    imagePath: String,
    baseUrl: String,
    imageSize: String
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data("${baseUrl}${imageSize}$imagePath")
            .crossfade(true)
            .build(),
        contentDescription = null
    )
}