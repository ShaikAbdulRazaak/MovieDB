package com.razzaaq.moviedb.ui.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.razzaaq.moviedb.domain.usecase.GetDashboardMoviesUseCase
import com.razzaaq.moviedb.domain.usecase.GetMovieDetailUseCase
import com.razzaaq.moviedb.ui.model.DataState
import com.razzaaq.moviedb.ui.model.DetailsUiState
import com.razzaaq.moviedb.ui.model.UiState
import com.razzaaq.moviedb.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val getDashboardMoviesUseCase: GetDashboardMoviesUseCase,
    private val getMovieDetailUseCase: GetMovieDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<DataState<UiState>>(DataState.Loading)
    val uiState: StateFlow<DataState<UiState>> = _uiState.asStateFlow()

    private val _detailsUiState = MutableStateFlow<DataState<DetailsUiState>>(DataState.Idle)
    val detailsUiState: StateFlow<DataState<DetailsUiState>> = _detailsUiState.asStateFlow()

    init {
        fetchDashboardData()
    }

    fun fetchDashboardData() = viewModelScope.launch {
        _uiState.value = DataState.Loading
        when (val result = getDashboardMoviesUseCase()) {
            is NetworkResult.Success -> _uiState.value = DataState.Success(result.data)
            is NetworkResult.Error -> _uiState.value = DataState.Error(result.exception.message ?: "Unknown Error")
            is NetworkResult.Loading -> _uiState.value = DataState.Loading
        }
    }

    fun getMovieDetail(movieId: Int) = viewModelScope.launch {
        _detailsUiState.value = DataState.Loading
        when (val result = getMovieDetailUseCase(movieId)) {
            is NetworkResult.Success -> _detailsUiState.value = DataState.Success(result.data)
            is NetworkResult.Error -> _detailsUiState.value = DataState.Error(result.exception.message ?: "Unknown Error")
            is NetworkResult.Loading -> _detailsUiState.value = DataState.Loading
        }
    }
}
