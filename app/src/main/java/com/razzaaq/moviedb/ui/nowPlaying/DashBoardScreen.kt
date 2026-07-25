package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
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
        ElevatedCard(
            onClick = { onCardClick(movie.id) },
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box {
                PosterImage(
                    imageUrl = movie.fullPosterUrl,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Featured",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun Section(
    title: String,
    movies: List<MovieUi>,
    onCardClick: (Int) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 4.dp, height = 24.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        RoundedCornerShape(2.dp)
                    )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            items(movies) { movie ->
                MovieCard(onCardClick, movie)
            }
        }
    }
}

@Composable
private fun MovieCard(
    onCardClick: (Int) -> Unit,
    movie: MovieUi
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(160.dp)
            .aspectRatio(2f / 3f)
            .padding(vertical = 4.dp, horizontal = 4.dp),
        onClick = { onCardClick(movie.id) }
    ) {
        Box {
            PosterImage(
                imageUrl = movie.fullPosterUrl,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.6f)),
                            startY = 100f
                        )
                    )
            )
            Text(
                text = movie.title,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )
        }
    }
}
