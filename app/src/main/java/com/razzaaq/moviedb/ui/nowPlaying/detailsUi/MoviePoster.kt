package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.ui.nowPlaying.PosterImage
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily

@Composable
fun MoviePoster(
    title: String,
    backdropPath: String,
    tagline: String,
    posterImage: Image,
    modifier: Modifier
) {
    Box {
        PosterImage(
            imagePath = backdropPath,
            baseUrl = posterImage.url,
            imageSize = posterImage.imageSize,
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .background(color = Color.Black.copy(alpha = 0.5f))
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier
                    .padding(horizontal = 16.dp),
                color = Color.White
            )
            if (tagline.isNotEmpty())
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    text = tagline,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = didactGothicFontFamily
                )
        }
    }
}

