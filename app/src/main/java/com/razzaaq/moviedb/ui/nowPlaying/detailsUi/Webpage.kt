package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R

@Composable
fun Webpage(homepage: String, modifier: Modifier = Modifier) {
    if (homepage.isNotEmpty()) {
        val uriHandler = LocalUriHandler.current
        Column(modifier = modifier) {
            MovieDetailHeading(stringResource(R.string.homepage))
            OutlinedButton(
                onClick = { uriHandler.openUri(homepage) },
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Text(text = "Official Website")
                Spacer(Modifier.padding(horizontal = 4.dp))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                    contentDescription = null,
                    modifier = Modifier.height(18.dp)
                )
            }
        }
    }
}
