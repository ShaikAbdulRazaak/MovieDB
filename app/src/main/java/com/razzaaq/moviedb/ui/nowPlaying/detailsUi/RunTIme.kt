package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R

@Composable
fun RunTime(modifier: Modifier = Modifier, runTime: Int) {
    Column {
        if (runTime != 0) {
            MovieDetailHeading("RunTime")
            val hours = runTime.div(60)
            val minutes = runTime.rem(60)
            val hourText = if (hours > 0)
                pluralStringResource(
                    R.plurals.runtime_hours,
                    hours, hours
                ) else ""
            val minuteText = if (minutes > 0)
                pluralStringResource(
                    R.plurals.runtime_minutes,
                    minutes, minutes
                ) else ""
            val formattedTime = listOf(hourText, minuteText)
                .filter { it.isNotEmpty() }
                .joinToString(" ")
            Text(
                text = formattedTime,
                modifier = modifier.padding(top = 8.dp)
            )
        }
    }
}