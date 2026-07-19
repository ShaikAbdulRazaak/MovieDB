package com.razzaaq.moviedb.ui.nowPlaying.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.api.ApiService
import com.razzaaq.moviedb.api.dto.ConfigurationDetail
import com.razzaaq.moviedb.api.dto.DetailsUiState
import com.razzaaq.moviedb.api.dto.Image
import com.razzaaq.moviedb.api.dto.Movie
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.api.dto.NowPlayingDto
import com.razzaaq.moviedb.api.dto.NowPlayingUiState
import com.razzaaq.moviedb.api.dto.PopularMoviesDto
import com.razzaaq.moviedb.api.dto.TopRatedMoviesDto
import com.razzaaq.moviedb.api.dto.UpcomingMoviesDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val nowPlayingFlow = MutableStateFlow(NowPlayingDto())
    private val topRatedFlow = MutableStateFlow(TopRatedMoviesDto())
    private val upcomingMoviesFlow = MutableStateFlow(UpcomingMoviesDto())
    private val popularMoviesFlow = MutableStateFlow(PopularMoviesDto())
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
        NowPlayingUiState(
            nowPlaying = nowPlaying.results.map { it.toMovie() },
            posterImage = configuration.toImage(),
            topRated = topRated.results.map { it.toMovie() },
            upComing = upComing.results.map { it.toMovie() },
            popular = popular.results.map { it.toMovie() }
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NowPlayingUiState()
    )

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        detailFlow.value = apiService.getMovieDetail(movieId)
    }

    val detailsUiState =
        combine(
            detailFlow,
            configurationData,
        ) { detailFlow, configuration ->
            DetailsUiState(
                movieDetail = detailFlow,
                posterImage = configuration.toImage(),
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailsUiState()
        )

}

private fun NowPlayingDto.Result.toMovie() = Movie(id = id, title = title, posterPath = posterPath)
private fun PopularMoviesDto.Result.toMovie() = Movie(id = id, title = title, posterPath = posterPath)
private fun TopRatedMoviesDto.Result.toMovie() = Movie(id = id, title = title, posterPath = posterPath)
private fun UpcomingMoviesDto.Result.toMovie() = Movie(id = id, title = title, posterPath = posterPath)

private fun ConfigurationDetail.toImage(): Image = Image(
    url = images.secureBaseUrl,
    imageSize = images.posterSizes.lastOrNull() ?: ""
)