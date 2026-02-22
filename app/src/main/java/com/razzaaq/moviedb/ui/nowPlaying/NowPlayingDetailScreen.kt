package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.Image
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily

@Composable
fun NowPlayingDetailScreen(
    modifier: Modifier = Modifier,
    posterImage: Image,
    movieDetail: MovieDetail
) {
    Column(
        modifier.fillMaxSize()
    ) {
        ElevatedCard(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            modifier = Modifier.padding(8.dp),
        ) {
            Box {
                PosterImage(
                    imagePath = movieDetail.backdropPath,
                    baseUrl = posterImage.url,
                    imageSize = posterImage.imageSize,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .background(color = Color.Black.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = movieDetail.title,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier
                            .padding(16.dp),
                        color = Color.White
                    )
                }
            }
        }
        Text(
            text = movieDetail.overview,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = didactGothicFontFamily,
            modifier = Modifier.padding(16.dp)
        )
    }
}