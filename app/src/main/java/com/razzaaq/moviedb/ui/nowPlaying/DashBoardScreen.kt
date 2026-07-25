package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.api.dto.MovieUi
import com.razzaaq.moviedb.api.dto.Movies

@Composable
fun DashBoardScreen(
    modifier: Modifier = Modifier,
    nowPlayingMovies: List<MovieUi>,
    popularMovies: List<MovieUi>,
    topRatedMovies: List<MovieUi>,
    upcomingMovies: List<MovieUi>,
    onCardClick: (movieId: Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(modifier.verticalScroll(scrollState)) {
        Section(title = Movies.NOW_PLAYING.value, movies = nowPlayingMovies, onCardClick = onCardClick)
        Section(title = Movies.UPCOMING.value, movies = upcomingMovies, onCardClick = onCardClick)
        Section(title = Movies.TOP_RATED.value, movies = topRatedMovies, onCardClick = onCardClick)
        Section(title = Movies.POPULAR.value, movies = popularMovies, onCardClick = onCardClick)
    }
}

@Composable
private fun Section(
    title: String,
    movies: List<MovieUi>,
    onCardClick: (Int) -> Unit
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    LazyHorizontalGrid(
        rows = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .height(280.dp)
    ) {
        items(movies) { movie ->
            MovieCard(onCardClick, movie)
        }
    }
}

@Composable
private fun MovieCard(
    onCardClick: (Int) -> Unit,
    movie: MovieUi
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(8.dp),
        onClick = { onCardClick(movie.id) }
    ) {
        PosterImage(
            imageUrl = movie.fullPosterUrl,
            modifier = Modifier.wrapContentSize()
        )
    }
}
