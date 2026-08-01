package com.razzaaq.moviedb.ui.dashboard.detailsUi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.ui.components.ErrorView
import com.razzaaq.moviedb.ui.components.LoadingView
import com.razzaaq.moviedb.ui.model.DataState
import com.razzaaq.moviedb.ui.model.DetailsUiState
import com.razzaaq.moviedb.ui.model.MovieDetailUi

@Composable
fun MovieDetailScreen(
    detailsUiState: DataState<DetailsUiState>,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (detailsUiState) {
            is DataState.Loading -> LoadingView()
            is DataState.Error -> ErrorView(message = detailsUiState.message, onRetry = onRetry)
            is DataState.Success -> MovieDetailContent(movieDetail = detailsUiState.data.movieDetail)
            is DataState.Idle -> Unit
        }
    }
}

@Composable
private fun MovieDetailContent(
    movieDetail: MovieDetailUi,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        item {
            MoviePoster(
                title = movieDetail.title,
                backdropPath = movieDetail.backdropPath,
                tagline = movieDetail.tagline
            )
        }
        item {
            MovieDetailItems(movieDetail = movieDetail)
        }
    }
}
