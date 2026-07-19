package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily

@Composable
fun Webpage(homepage: String, modifier: Modifier = Modifier) {
    if (homepage.isNotEmpty())
        Column(modifier = modifier) {
            MovieDetailHeading(
                stringResource(R.string.homepage)
            )
            Text(
                buildAnnotatedString {
                    withLink(
                        link = LinkAnnotation.Url(homepage)
                    ) { append(homepage) }
                },
                fontFamily = didactGothicFontFamily,
            )
        }
}
