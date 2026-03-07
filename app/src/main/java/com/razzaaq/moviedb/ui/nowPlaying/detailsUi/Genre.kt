package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.api.dto.MovieDetail

@Composable
fun Genre(modifier: Modifier = Modifier, genres: List<MovieDetail.Genre>) {
    if (genres.isNotEmpty())
        Column {
            MovieDetailHeading(
                title = stringResource(R.string.genre),
                modifier = modifier.padding(bottom = 8.dp)
            )
            FlowRow(
                modifier = modifier,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                for (genre in genres) {
                    ElevatedCard(
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(text = genre.name, modifier = modifier.padding(8.dp))
                    }
                }

            }
        }
}