package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R

@Composable
fun Genre(genres: String, modifier: Modifier = Modifier) {
    if (genres.isNotEmpty())
        Column(modifier = modifier) {
            MovieDetailHeading(
                title = stringResource(R.string.genre)
            )
            Text(
                text = genres,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
}