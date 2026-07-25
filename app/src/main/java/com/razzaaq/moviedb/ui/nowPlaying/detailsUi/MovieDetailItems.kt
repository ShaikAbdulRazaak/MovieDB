package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.MovieDetailUi

@Composable
fun MovieDetailItems(
    movieDetail: MovieDetailUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        if (movieDetail.overview.isNotEmpty()) {
            Overview(overview = movieDetail.overview)
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }

        if (movieDetail.genres.isNotEmpty()) {
            Genre(genres = movieDetail.genres)
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        }

        Surface(
            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            shape = MaterialTheme.shapes.medium
        ) {
            MovieDetailSummary(
                movieDetail,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (movieDetail.productionCompanies.isNotEmpty()) {
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            ProductionCompanies(productionCompanies = movieDetail.productionCompanies)
        }

        if (movieDetail.homepage.isNotEmpty()) {
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Webpage(homepage = movieDetail.homepage)
        }
    }
}

@Composable
private fun MovieDetailSummary(
    movieDetail: MovieDetailUi,
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        RunTime(runtime = movieDetail.runtime)
        ReleaseDate(releaseDate = movieDetail.releaseDate)
        Revenue(revenue = movieDetail.revenue)
        Budget(budget = movieDetail.budget)
    }
}
