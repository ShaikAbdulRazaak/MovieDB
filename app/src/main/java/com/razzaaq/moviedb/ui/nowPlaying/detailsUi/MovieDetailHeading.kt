package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.razzaaq.moviedb.ui.theme.ubuntuFontFamily

@Composable
fun MovieDetailHeading(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontFamily = ubuntuFontFamily,
        style = MaterialTheme.typography.bodyLarge,
        textDecoration = TextDecoration.Underline,
        modifier = modifier
    )
}