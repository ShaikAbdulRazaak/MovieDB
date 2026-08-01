package com.razzaaq.moviedb.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.ui.components.BrandedHeader
import com.razzaaq.moviedb.ui.components.ErrorView
import com.razzaaq.moviedb.ui.components.LoadingView
import com.razzaaq.moviedb.ui.components.MovieCard
import com.razzaaq.moviedb.ui.model.DataState
import com.razzaaq.moviedb.ui.model.MovieUi
import com.razzaaq.moviedb.ui.model.Movies
import com.razzaaq.moviedb.ui.model.UiState

@Composable
fun DashBoardScreen(
    uiState: DataState<UiState>,
    onRetry: () -> Unit,
    onCardClick: (movieId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is DataState.Loading -> LoadingView()
            is DataState.Error -> ErrorView(message = uiState.message, onRetry = onRetry)
            is DataState.Success -> DashBoardContent(
                uiState = uiState.data,
                onCardClick = onCardClick
            )
            is DataState.Idle -> Unit
        }
    }
}

@Composable
private fun DashBoardContent(
    uiState: UiState,
    onCardClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (uiState.nowPlaying.isNotEmpty()) {
            HeroSection(
                movie = uiState.nowPlaying.first(),
                onCardClick = onCardClick
            )
        }
        Section(
            title = Movies.NOW_PLAYING.value,
            movies = uiState.nowPlaying,
            onCardClick = onCardClick
        )
        Section(title = Movies.UPCOMING.value, movies = uiState.upComing, onCardClick = onCardClick)
        Section(title = Movies.TOP_RATED.value, movies = uiState.topRated, onCardClick = onCardClick)
        Section(title = Movies.POPULAR.value, movies = uiState.popular, onCardClick = onCardClick)
    }
}

@Composable
private fun HeroSection(
    movie: MovieUi,
    onCardClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .padding(16.dp)
    ) {
        MovieCard(
            movie = movie,
            onClick = onCardClick,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "Featured",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 32.dp, bottom = 48.dp)
        )
    }
}

@Composable
private fun Section(
    title: String,
    movies: List<MovieUi>,
    onCardClick: (Int) -> Unit
) {
    Column {
        BrandedHeader(
            title = title,
            showAccent = true,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            items(movies) { movie ->
                MovieCard(
                    movie = movie,
                    onClick = onCardClick,
                    modifier = Modifier
                        .width(160.dp)
                        .aspectRatio(2f / 3f)
                        .padding(vertical = 4.dp, horizontal = 4.dp)
                )
            }
        }
    }
}
