package com.razzaaq.moviedb.ui.dashboard.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.R
import com.razzaaq.moviedb.data.api.ApiService
import com.razzaaq.moviedb.data.api.dto.ConfigurationDetail
import com.razzaaq.moviedb.ui.model.DetailsUiState
import com.razzaaq.moviedb.ui.model.Image
import com.razzaaq.moviedb.data.api.dto.MovieDetail
import com.razzaaq.moviedb.ui.model.MovieDetailUi
import com.razzaaq.moviedb.data.api.dto.MovieResponseDto
import com.razzaaq.moviedb.data.api.dto.MovieResultDto
import com.razzaaq.moviedb.ui.model.MovieUi
import com.razzaaq.moviedb.ui.model.ProductionCompanyUi
import com.razzaaq.moviedb.ui.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import java.text.NumberFormat
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val apiService: ApiService,
    private val application: Application
) : ViewModel() {

    private val nowPlayingFlow = MutableStateFlow(MovieResponseDto())
    private val topRatedFlow = MutableStateFlow(MovieResponseDto())
    private val upcomingMoviesFlow = MutableStateFlow(MovieResponseDto())
    private val popularMoviesFlow = MutableStateFlow(MovieResponseDto())
    private val configurationData = MutableStateFlow(ConfigurationDetail())
    private val detailFlow = MutableStateFlow(MovieDetail())

    init {
        viewModelScope.launch {
            val configurationDeferred = async { apiService.getTMDBConfiguration() }
            val nowPlayingDeferred = async { apiService.getNowPlayingMovies() }
            val upcomingMovies = async { apiService.getUpcomingMovies() }
            val topRatedMovies = async { apiService.getTopRated() }
            val popularMovies = async { apiService.getPopular() }

            configurationData.value = configurationDeferred.await()
            nowPlayingFlow.value = nowPlayingDeferred.await()
            topRatedFlow.value = topRatedMovies.await()
            upcomingMoviesFlow.value = upcomingMovies.await()
            popularMoviesFlow.value = popularMovies.await()
        }
    }

    val uiState = combine(
        configurationData,
        nowPlayingFlow,
        upcomingMoviesFlow,
        popularMoviesFlow,
        topRatedFlow
    ) { configuration, nowPlaying, upComing, popular, topRated ->
        val posterImage = configuration.toImage()
        UiState(
            nowPlaying = nowPlaying.results.map { it.toUi(posterImage) },
            topRated = topRated.results.map { it.toUi(posterImage) },
            upComing = upComing.results.map { it.toUi(posterImage) },
            popular = popular.results.map { it.toUi(posterImage) }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState()
    )

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        detailFlow.value = apiService.getMovieDetail(movieId)
    }

    val detailsUiState =
        combine(
            detailFlow,
            configurationData,
        ) { detailFlow, configuration ->
            val posterImage = configuration.toImage()
            DetailsUiState(
                movieDetail = detailFlow.toUi(posterImage),
                posterImage = posterImage,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailsUiState()
        )

    private fun MovieDetail.toUi(posterImage: Image): MovieDetailUi {
        val currencyFormat = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
        }

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

        val genresList = genres.map { it.name }

        val hours = runtime.div(60)
        val minutes = runtime.rem(60)
        val hourText = if (hours > 0)
            application.resources.getQuantityString(
                R.plurals.runtime_hours,
                hours, hours
            ) else ""
        val minuteText = if (minutes > 0)
            application.resources.getQuantityString(
                R.plurals.runtime_minutes,
                minutes, minutes
            ) else ""
        val formattedTime = listOf(hourText, minuteText)
            .filter { it.isNotEmpty() }
            .joinToString(" ")

        return MovieDetailUi(
            id = id,
            title = title,
            backdropPath = "${posterImage.url}${posterImage.imageSize}${backdropPath.ifEmpty { posterPath }}",
            tagline = tagline,
            overview = overview,
            budget = if (budget != 0) currencyFormat.format(budget) else "",
            revenue = if (revenue != 0) currencyFormat.format(revenue) else "",
            releaseDate = formattedReleaseDate,
            runtime = formattedTime,
            genres = genresList,
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

}

private fun MovieResultDto.toUi(posterImage: Image) = MovieUi(
    id = id,
    title = title,
    fullPosterUrl = "${posterImage.url}${posterImage.imageSize}$posterPath"
)

private fun ConfigurationDetail.toImage(): Image = Image(
    url = images.secureBaseUrl,
    imageSize = images.posterSizes.lastOrNull() ?: ""
)