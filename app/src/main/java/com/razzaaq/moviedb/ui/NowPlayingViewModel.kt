package com.razzaaq.moviedb.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.api.ApiService
import com.razzaaq.moviedb.api.dto.NowPlaying
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(apiService: ApiService) : ViewModel() {

    val nowPlayingFlow = MutableStateFlow(NowPlaying())

    init {
        viewModelScope.launch {
            val nowPlayingMovies = async { apiService.getNowPlayingMovies() }
            nowPlayingFlow.value = nowPlayingMovies.await()
        }
    }

}