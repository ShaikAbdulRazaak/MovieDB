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
fun Budget(modifier: Modifier = Modifier, budget: Int) {
    if (budget != 0)
        Column {
            MovieDetailHeading("Budget")
            val formattedRevenue = NumberFormat.getCurrencyInstance().apply {
                maximumFractionDigits = 0
            }.format(budget)
            Text(
                text = formattedRevenue,
                modifier = modifier.padding(top = 8.dp),
                fontFamily = didactGothicFontFamily
            )
        }
}