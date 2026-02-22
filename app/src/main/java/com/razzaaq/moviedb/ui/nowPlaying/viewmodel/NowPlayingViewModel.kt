package com.razzaaq.moviedb.ui.nowPlaying.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.api.ApiService
import com.razzaaq.moviedb.api.dto.NowPlayingDto
import com.razzaaq.moviedb.api.dto.TMDBConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(apiService: ApiService) : ViewModel() {

    val nowPlayingFlow = MutableStateFlow(NowPlayingDto())

    val configurationData = MutableStateFlow(TMDBConfiguration())

    init {
        viewModelScope.launch {
            configurationData.value = apiService.getTMDBConfiguration()
            nowPlayingFlow.value = apiService.getNowPlayingMovies()
        }
    }

}