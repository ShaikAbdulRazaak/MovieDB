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
import com.razzaaq.moviedb.ui.theme.MontserratFontFamily

@Composable
fun Overview(overview: String, modifier: Modifier) {
    if (overview.isNotEmpty())
        Column {
            MovieDetailHeading(
                title = stringResource(R.string.overview),
                modifier = modifier
            )
            Text(
                text = overview,
                style = MaterialTheme.typography.bodyMedium,
                fontFamily = MontserratFontFamily,
                modifier = modifier.padding(top = 8.dp)
            )
        }
}