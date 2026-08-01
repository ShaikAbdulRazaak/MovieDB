package com.razzaaq.moviedb.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.data.dto.ConfigurationDetail
import com.razzaaq.moviedb.data.dto.MovieDetail
import com.razzaaq.moviedb.data.dto.MovieResponseDto
import com.razzaaq.moviedb.data.mapper.MovieMappers
import com.razzaaq.moviedb.data.repository.MovieRepository
import com.razzaaq.moviedb.ui.model.DetailsUiState
import com.razzaaq.moviedb.ui.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val movieMappers: MovieMappers
) : ViewModel() {

    private val nowPlayingFlow = MutableStateFlow(MovieResponseDto())
    private val topRatedFlow = MutableStateFlow(MovieResponseDto())
    private val upcomingMoviesFlow = MutableStateFlow(MovieResponseDto())
    private val popularMoviesFlow = MutableStateFlow(MovieResponseDto())
    private val configurationData = MutableStateFlow(ConfigurationDetail())
    private val detailFlow = MutableStateFlow(MovieDetail())

    init {
        viewModelScope.launch {
            val configurationDeferred = async { repository.getTMDBConfiguration() }
            val nowPlayingDeferred = async { repository.getNowPlayingMovies() }
            val upcomingMovies = async { repository.getUpcomingMovies() }
            val topRatedMovies = async { repository.getTopRated() }
            val popularMovies = async { repository.getPopular() }

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
        with(movieMappers) {
            val posterImage = configuration.toImage()
            UiState(
                nowPlaying = nowPlaying.results.map { it.toUi(posterImage) },
                topRated = topRated.results.map { it.toUi(posterImage) },
                upComing = upComing.results.map { it.toUi(posterImage) },
                popular = popular.results.map { it.toUi(posterImage) }
            )
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UiState()
    )

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        detailFlow.value = repository.getMovieDetail(movieId)
    }

    val detailsUiState =
        combine(
            detailFlow,
            configurationData,
        ) { detail, configuration ->
            with(movieMappers) {
                val posterImage = configuration.toImage()
                DetailsUiState(
                    movieDetail = detail.toUi(posterImage),
                    posterImage = posterImage,
                )
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DetailsUiState()
        )
}
