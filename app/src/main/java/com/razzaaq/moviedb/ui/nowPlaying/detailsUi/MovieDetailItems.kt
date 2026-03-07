package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.MovieDetail

@Composable
fun MovieDetailItems(
    modifier: Modifier,
    movieDetail: MovieDetail,
    posterImage: Image
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Overview(
            overview = movieDetail.overview,
            modifier = modifier
        )
        Genre(
            modifier = modifier,
            genres = movieDetail.genres
        )
        RunTime(
            modifier = modifier,
            runTime = movieDetail.runtime
        )
        Webpage(
            homepage = movieDetail.homepage,
            modifier = modifier,
        )
        ProductionCompanies(
            productionCompanies = movieDetail.productionCompanies,
            posterImage = posterImage,
            modifier = modifier,
        )
    }
}