package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

@Composable
fun ReleaseDate(modifier: Modifier = Modifier, releaseDate: String) {
    if (releaseDate.isNotEmpty()) {
        Column(modifier) {
            MovieDetailHeading("Release Date")
            val formattedReleaseDate = runCatching {
                val date = LocalDate.parse(releaseDate)
                val format = LocalDate.Format {
                    day()
                    char(' ')
                    monthName(MonthNames.ENGLISH_FULL)
                    char(' ')
                    year()
                }
                date.format(format)
            }.getOrElse { releaseDate }
            Text(
                formattedReleaseDate,
                fontFamily = didactGothicFontFamily,
                modifier = modifier.padding(top = 8.dp)
            )
        }
    }
}