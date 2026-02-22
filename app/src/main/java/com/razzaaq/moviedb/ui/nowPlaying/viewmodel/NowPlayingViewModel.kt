package com.razzaaq.moviedb.ui.nowPlaying.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.api.ApiService
import com.razzaaq.moviedb.api.dto.ConfigurationDetail
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.api.dto.NowPlayingDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val nowPlayingFlow = MutableStateFlow(NowPlayingDto())
    private val configurationData = MutableStateFlow(ConfigurationDetail())
    private val nowPlayingDetailsFlow = MutableStateFlow(MovieDetail())

    val uiState = combine(nowPlayingFlow, configurationData) { nowPlaying, configuration ->
        NowPlayingUiState(
            nowPlaying.results.map { it.toMovie() },
            configuration.toImage()
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = NowPlayingUiState()
    )

    init {
        viewModelScope.launch {
            val configurationDeferred = async { apiService.getTMDBConfiguration() }
            val nowPlayingDeferred = async { apiService.getNowPlayingMovies() }

            configurationData.value = configurationDeferred.await()
            nowPlayingFlow.value = nowPlayingDeferred.await()
        }
    }

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        nowPlayingDetailsFlow.value = apiService.getMovieDetail(movieId)
    }

    val detailsUiState =
        combine(nowPlayingDetailsFlow, configurationData) { nowPlayingDetails, configuration ->
            NowPlayingDetailsUiState(
                nowPlayingDetails,
                configuration.toImage()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NowPlayingDetailsUiState()
        )

}

private fun NowPlayingDto.Result.toMovie() = Movie(id = id, title = title, posterPath = posterPath)
private fun ConfigurationDetail.toImage(): Image = Image(
    url = images.secureBaseUrl,
    imageSize = images.posterSizes.lastOrNull() ?: ""
)

data class NowPlayingUiState(
    val nowPlaying: List<Movie> = listOf<Movie>(),
    val posterImage: Image = Image(),
)

data class NowPlayingDetailsUiState(
    val nowPlayingDetails: MovieDetail = MovieDetail(),
    val posterImage: Image = Image()
)

data class Image(val url: String = "", val imageSize: String = "")

data class Movie(val id: Int = 0, val title: String = "", val posterPath: String = "")
