package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.MovieDetail
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
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MoviePoster(movieDetail, posterImage, modifier)
        Overview(movieDetail, modifier)
        ProductionCompanies(movieDetail, posterImage)
    }
}

@Composable
private fun MoviePoster(
    movieDetail: MovieDetail,
    posterImage: Image,
    modifier: Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
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
                        .padding(horizontal = 16.dp),
                    color = Color.White
                )
                if (movieDetail.tagline.isNotEmpty())
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        text = movieDetail.tagline,
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = didactGothicFontFamily
                    )
            }
        }
    }
}

@Composable
private fun Overview(movieDetail: MovieDetail, modifier: Modifier) {
    Text(
        stringResource(R.string.overview),
        fontFamily = ubuntuFontFamily,
        style = MaterialTheme.typography.bodyLarge,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
    )
    Text(
        text = movieDetail.overview,
        style = MaterialTheme.typography.bodyMedium,
        fontFamily = MontserratFontFamily,
        modifier = modifier
    )
}


@Composable
private fun ProductionCompanies(
    movieDetail: MovieDetail,
    posterImage: Image
) {
    if (movieDetail.productionCompanies.any { it.logoPath.isNotEmpty() }) {
        Text(
            text = stringResource(R.string.production_companies),
            fontFamily = ubuntuFontFamily,
            style = MaterialTheme.typography.bodyLarge,
            textDecoration = TextDecoration.Underline
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(movieDetail.productionCompanies) { productionCompany ->
                if (productionCompany.logoPath.isNotEmpty())
                    FlowRow {
                        PosterImage(
                            imagePath = productionCompany.logoPath,
                            baseUrl = posterImage.url,
                            imageSize = "w300",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 8.dp)
                        )
                        Text(
                            text = productionCompany.name,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                                .align(Alignment.CenterVertically),
                            fontFamily = didactGothicFontFamily
                        )
                        Text(
                            text = productionCompany.originCountry,
                            fontFamily = didactGothicFontFamily,
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }

            }
        }
    }
}