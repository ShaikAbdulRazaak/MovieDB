package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.ui.theme.didactGothicFontFamily
import java.text.NumberFormat

@Composable
fun Revenue(
    modifier: Modifier = Modifier,
    revenue: Int
) {
    if (revenue != 0)
        Column {
            MovieDetailHeading("Revenue")
            val formattedRevenue = NumberFormat.getCurrencyInstance().apply {
                maximumFractionDigits = 0
            }.format(revenue)
            Text(
                text = formattedRevenue,
                modifier = modifier.padding(top = 8.dp),
                fontFamily = didactGothicFontFamily
            )
        }
}