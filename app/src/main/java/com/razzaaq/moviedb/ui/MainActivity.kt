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
import com.razzaaq.moviedb.ui.nowPlaying.DashBoardScreen
import com.razzaaq.moviedb.ui.nowPlaying.Dashboard
import com.razzaaq.moviedb.ui.nowPlaying.Detail
import com.razzaaq.moviedb.ui.nowPlaying.detailsUi.MovieDetailScreen
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.DashBoardViewModel
import com.razzaaq.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val dashBoardViewModel by viewModels<DashBoardViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MovieDBTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val backStack = remember { mutableStateListOf<Any>(Dashboard) }

                    NavDisplay(
                        backStack = backStack,
                        modifier = Modifier.padding(innerPadding),
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        entryProvider = { key ->
                            when (key) {
                                is Dashboard -> NavEntry(key = key) {
                                    val uiState by dashBoardViewModel.uiState.collectAsState()
                                    DashBoardScreen(
                                        nowPlayingMovies = uiState.nowPlaying,
                                        topRatedMovies = uiState.topRated,
                                        upcomingMovies = uiState.upComing,
                                        popularMovies = uiState.popular,
                                        posterImage = uiState.posterImage,
                                        onCardClick = {
                                            backStack.add(Detail(it))
                                            dashBoardViewModel.getMovieDetail(it)
                                        }
                                    )
                                }

                                is Detail -> NavEntry(key = key) {
                                    val detailsUiState by dashBoardViewModel.detailsUiState.collectAsState()
                                    MovieDetailScreen(
                                        movieDetail = detailsUiState.movieDetail,
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