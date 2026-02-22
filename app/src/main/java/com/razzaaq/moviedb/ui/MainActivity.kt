package com.razzaaq.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.razzaaq.moviedb.ui.nowPlaying.NowPlaying
import com.razzaaq.moviedb.ui.nowPlaying.NowPlayingDetail
import com.razzaaq.moviedb.ui.nowPlaying.NowPlayingDetailScreen
import com.razzaaq.moviedb.ui.nowPlaying.NowPlayingScreen
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.NowPlayingViewModel
import com.razzaaq.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val nowPlayingViewModel by viewModels<NowPlayingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MovieDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val backStack = remember { mutableStateListOf<Any>(NowPlaying) }

                    NavDisplay(
                        backStack = backStack,
                        modifier = Modifier.padding(innerPadding),
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        entryProvider = { key ->
                            when (key) {
                                is NowPlaying -> NavEntry(key = key) {
                                    val uiState by nowPlayingViewModel.uiState.collectAsState()
                                    NowPlayingScreen(
                                        movies = uiState.nowPlaying,
                                        posterImage = uiState.posterImage,
                                        onCardClick = {
                                            backStack.add(NowPlayingDetail(it))
                                            nowPlayingViewModel.getMovieDetail(it)
                                        }
                                    )
                                }

                                is NowPlayingDetail -> NavEntry(key = key) {
                                    val detailsUiState by nowPlayingViewModel.detailsUiState.collectAsState()
                                    NowPlayingDetailScreen(
                                        movieDetail = detailsUiState.nowPlayingDetails,
                                        posterImage = detailsUiState.posterImage
                                    )
                                }

                                else -> error("Unknown route: $key")
                            }
                        }
                    )
                }
            }
        }
    }
}