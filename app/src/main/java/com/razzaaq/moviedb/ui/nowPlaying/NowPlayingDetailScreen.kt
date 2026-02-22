package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.Image
import com.razzaaq.moviedb.ui.theme.MontserratFontFamily
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily
import com.razzaaq.moviedb.ui.theme.ubuntuFontFamily

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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                        .background(color = Color.Black.copy(alpha = 0.5f))
                ) {
                    Text(
                        text = movieDetail.title,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                        color = Color.White
                    )
                    if (movieDetail.tagline.isNotEmpty())
                        Text(
                            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp, end = 16.dp),
                            text = movieDetail.tagline,
                            color = Color.White,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = didactGothicFontFamily
                        )
                }
            }
        }
        Text(
            "Overview",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
            fontFamily = ubuntuFontFamily,
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = TextDecoration.Underline
        )
        Text(
            text = movieDetail.overview,
            style = MaterialTheme.typography.bodyMedium,
            fontFamily = MontserratFontFamily,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Production Companies",
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            fontFamily = ubuntuFontFamily,
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = TextDecoration.Underline
        )
        FlowRow(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            for (productionCompany in movieDetail.productionCompanies) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (productionCompany.logoPath.isNotEmpty())
                        PosterImage(
                            imagePath = productionCompany.logoPath,
                            baseUrl = posterImage.url,
                            imageSize = posterImage.imageSize,
                            modifier = Modifier.size(92.dp)
                        )
                }
            }
        }
    }
}