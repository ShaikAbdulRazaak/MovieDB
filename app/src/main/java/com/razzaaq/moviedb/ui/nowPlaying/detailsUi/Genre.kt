package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily

@Composable
fun Genre(modifier: Modifier = Modifier, genres: List<MovieDetail.Genre>) {
    if (genres.isNotEmpty())
        Column {
            MovieDetailHeading(
                title = stringResource(R.string.genre),
                modifier = modifier
            )
            FlowRow(
                modifier = modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (genres.isNotEmpty()) {
                    val genre = when (genres.size) {
                        1 -> genres.first().name
                        else -> {
                            val genresString = genres.dropLast(1).joinToString(", ") { it.name }
                            "$genresString and ${genres.last().name}"
                        }
                    }
                    Text(genre, fontFamily = didactGothicFontFamily)
                }
            }
        }
}