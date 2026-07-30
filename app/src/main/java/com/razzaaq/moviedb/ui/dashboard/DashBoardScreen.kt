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
import com.razzaaq.moviedb.api.dto.MovieUi
import com.razzaaq.moviedb.api.dto.Movies
import com.razzaaq.moviedb.ui.components.BrandedHeader
import com.razzaaq.moviedb.ui.components.MovieCard

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
    Column(
        modifier
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        if (nowPlayingMovies.isNotEmpty()) {
            HeroSection(
                movie = nowPlayingMovies.first(),
                onCardClick = onCardClick
            )
        }
        Section(
            title = Movies.NOW_PLAYING.value,
            movies = nowPlayingMovies,
            onCardClick = onCardClick
        )
        Section(title = Movies.UPCOMING.value, movies = upcomingMovies, onCardClick = onCardClick)
        Section(title = Movies.TOP_RATED.value, movies = topRatedMovies, onCardClick = onCardClick)
        Section(title = Movies.POPULAR.value, movies = popularMovies, onCardClick = onCardClick)
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
                .padding(start = 32.dp, bottom = 48.dp) // Adjusted for MovieCard internal padding
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
