package com.razzaaq.moviedb.ui.nowPlaying.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.api.dto.ProductionCompanyUi
import com.razzaaq.moviedb.ui.nowPlaying.PosterImage

@Composable
fun ProductionCompanies(
    productionCompanies: List<ProductionCompanyUi>,
    modifier: Modifier = Modifier
) {
    if (productionCompanies.any { it.logoUrl.isNotEmpty() }) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            MovieDetailHeading(title = stringResource(R.string.production_companies))
            productionCompanies.forEach { productionCompany ->
                if (productionCompany.logoUrl.isNotEmpty()) {
                    ProductionCompany(productionCompany)
                }
            }
        }
    }
}

@Composable
private fun ProductionCompany(
    productionCompany: ProductionCompanyUi,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(8.dp)) {
        PosterImage(
            imageUrl = productionCompany.logoUrl,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(end = 8.dp)
        )
        Text(
            text = productionCompany.name,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = productionCompany.originCountry,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}