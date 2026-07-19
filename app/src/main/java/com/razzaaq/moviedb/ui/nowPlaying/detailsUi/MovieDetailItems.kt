package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.MovieDetail

@Composable
fun MovieDetailItems(
    movieDetail: MovieDetail, posterImage: Image, modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Overview(
            overview = movieDetail.overview
        )
        MovieDetailSummary(movieDetail)
        ProductionCompanies(
            productionCompanies = movieDetail.productionCompanies,
            posterImage = posterImage
        )
        Webpage(
            homepage = movieDetail.homepage
        )
    }
}

@Composable
private fun MovieDetailSummary(
    movieDetail: MovieDetail,
    modifier: Modifier = Modifier
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Genre(
            genres = movieDetail.genres
        )
        RunTime(
            runTime = movieDetail.runtime
        )
        ReleaseDate(
            releaseDate = movieDetail.releaseDate
        )
        Revenue(
            revenue = movieDetail.revenue
        )
        Budget(
            budget = movieDetail.budget
        )
    }
}
