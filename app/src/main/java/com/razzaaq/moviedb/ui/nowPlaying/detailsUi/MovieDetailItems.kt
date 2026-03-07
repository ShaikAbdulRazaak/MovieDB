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
    modifier: Modifier, movieDetail: MovieDetail, posterImage: Image
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Overview(
            overview = movieDetail.overview, modifier = modifier
        )
        MovieDetailSummary(modifier, movieDetail)
        ProductionCompanies(
            productionCompanies = movieDetail.productionCompanies,
            posterImage = posterImage,
            modifier = modifier,
        )
        Webpage(
            homepage = movieDetail.homepage,
            modifier = modifier,
        )
    }
}

@Composable
private fun MovieDetailSummary(
    modifier: Modifier,
    movieDetail: MovieDetail
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Genre(
            modifier = modifier, genres = movieDetail.genres
        )
        RunTime(
            modifier = modifier, runTime = movieDetail.runtime
        )
        ReleaseDate(
            modifier = modifier,
            releaseDate = movieDetail.releaseDate
        )
        Revenue(
            revenue = movieDetail.revenue,
            modifier = modifier
        )
        Budget(
            modifier = modifier,
            budget = movieDetail.budget
        )
    }
}