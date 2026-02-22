package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.Image
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.Movie
import com.razzaaq.moviedb.ui.theme.ubuntuFontFamily

@Composable
fun NowPlayingScreen(
    modifier: Modifier = Modifier,
    movies: List<Movie> = listOf(),
    posterImage: Image,
    onCardClick: (movieId: Int) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        items(movies) {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                modifier = Modifier.padding(8.dp),
                onClick = { onCardClick(it.id) }
            ) {
                PosterImage(
                    modifier = modifier,
                    imagePath = it.posterPath,
                    baseUrl = posterImage.url,
                    imageSize = posterImage.imageSize
                )
                Text(
                    text = it.title,
                    fontFamily = ubuntuFontFamily,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}