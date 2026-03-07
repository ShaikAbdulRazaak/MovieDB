package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.ui.nowPlaying.PosterImage
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily

@Composable
fun ProductionCompanies(
    productionCompanies: List<MovieDetail.ProductionCompany>,
    posterImage: Image,
    modifier: Modifier = Modifier
) {
    if (productionCompanies.any { it.logoPath.isNotEmpty() }) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            MovieDetailHeading(title = stringResource(R.string.production_companies))
            productionCompanies.forEach { productionCompany ->
                if (productionCompany.logoPath.isNotEmpty()) {
                    ProductionCompany(productionCompany, posterImage)
                }
            }
        }
    }
}

@Composable
private fun ProductionCompany(
    productionCompany: MovieDetail.ProductionCompany,
    posterImage: Image,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = modifier.padding(8.dp)) {
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
