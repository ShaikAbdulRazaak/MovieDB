package com.razzaaq.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.razzaaq.moviedb.api.dto.TMDBConfiguration
import com.razzaaq.moviedb.ui.theme.JosefinSansFontFamily
import com.razzaaq.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewmodel by viewModels<NowPlayingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MovieDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state = viewmodel.nowPlayingFlow.collectAsStateWithLifecycle().value
                    val configuration =
                        viewmodel.configurationData.collectAsStateWithLifecycle().value
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Adaptive(96.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalItemSpacing = 16.dp,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(24.dp)
                    ) {
                        items(state.results) {
                            Card {
                                PosterImage(
                                    imagePath = it.posterPath,
                                    configuration = configuration
                                )
                                Text(
                                    text = it.title,
                                    fontFamily = JosefinSansFontFamily,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PosterImage(
        imagePath: String,
        configuration: TMDBConfiguration
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data("${configuration.images.secureBaseUrl}${configuration.images.posterSizes.last()}$imagePath")
                .crossfade(true)
                .build(),
            contentDescription = null
        )
    }
}