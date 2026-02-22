package com.razzaaq.moviedb.ui.nowPlaying.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.api.ApiService
import com.razzaaq.moviedb.api.dto.NowPlayingDto
import com.razzaaq.moviedb.api.dto.TMDBConfiguration
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
    private val configurationData = MutableStateFlow(TMDBConfiguration())

    val uiState = combine(nowPlayingFlow, configurationData) { nowPlaying, configuration ->
        NowPlayingUiState(nowPlaying, configuration)
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
}

data class NowPlayingUiState(
    val nowPlaying: NowPlayingDto = NowPlayingDto(),
    val configuration: TMDBConfiguration = TMDBConfiguration()
)
