package com.razzaaq.moviedb.data.mapper

import android.content.ContentValues.TAG
import android.util.Log
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.data.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.dto.MovieDetail
import com.razzaaq.moviedb.data.dto.MovieResultDto
import com.razzaaq.moviedb.ui.model.Image
import com.razzaaq.moviedb.ui.model.MovieDetailUi
import com.razzaaq.moviedb.ui.model.MovieUi
import com.razzaaq.moviedb.ui.model.ProductionCompanyUi
import com.razzaaq.moviedb.util.StringProvider
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import java.text.NumberFormat
import javax.inject.Inject

class MovieMappers @Inject constructor(private val stringProvider: StringProvider) {

    private val currencyFormat = NumberFormat.getCurrencyInstance().apply {
        maximumFractionDigits = 0
    }

    private val dateFormatter = LocalDate.Format {
        day()
        char(' ')
        monthName(MonthNames.ENGLISH_FULL)
        char(' ')
        year()
    }

    fun MovieResultDto.toUi(posterImage: Image) = MovieUi(
        id = id,
        title = title,
        fullPosterUrl = "${posterImage.url}${posterImage.imageSize}$posterPath"
    )

    fun ConfigurationDetail.toImage(): Image = Image(
        url = images.secureBaseUrl,
        imageSize = images.posterSizes.lastOrNull() ?: ""
    )

    fun MovieDetail.toUi(posterImage: Image): MovieDetailUi {
        return MovieDetailUi(
            id = id,
            title = title,
            backdropPath = "${posterImage.url}${posterImage.imageSize}${backdropPath.ifEmpty { posterPath }}",
            tagline = tagline,
            overview = overview,
            budget = formatCurrency(budget),
            revenue = formatCurrency(revenue),
            releaseDate = formatDate(releaseDate),
            runtime = formatRuntime(runtime),
            genres = genres.map { it.name },
            homepage = homepage,
            productionCompanies = productionCompanies.map {
                ProductionCompanyUi(
                    id = it.id,
                    logoUrl = if (it.logoPath.isNotEmpty()) "${posterImage.url}w300${it.logoPath}" else "",
                    name = it.name,
                    originCountry = it.originCountry
                )
            }
        )
    }

    private fun formatRuntime(runtime: Int): String = buildString {
        val hours = runtime / 60
        val minutes = runtime % 60
        if (hours > 0)
            append(stringProvider.getQuantityString(R.plurals.runtime_hours, hours, hours))
        if (minutes > 0) {
            if (isNotEmpty()) append(" ")
            append(stringProvider.getQuantityString(R.plurals.runtime_minutes, minutes, minutes))
        }
    }

    private fun formatDate(dateString: String): String = runCatching {
        LocalDate.parse(dateString).format(dateFormatter)
    }.getOrElse { onFailure ->
        Log.e(TAG, "formatDate: ${onFailure.message}", onFailure)
        dateString
    }

    private fun formatCurrency(amount: Int): String =
        if (amount > 0) currencyFormat.format(amount) else ""
}
