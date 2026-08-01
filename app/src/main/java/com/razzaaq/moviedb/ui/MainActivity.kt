package com.razzaaq.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.razzaaq.moviedb.ui.dashboard.DashBoardScreen
import com.razzaaq.moviedb.ui.dashboard.Dashboard
import com.razzaaq.moviedb.ui.dashboard.Detail
import com.razzaaq.moviedb.ui.dashboard.detailsUi.MovieDetailScreen
import com.razzaaq.moviedb.ui.dashboard.viewmodel.DashBoardViewModel
import com.razzaaq.moviedb.ui.theme.MovieDBTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dashBoardViewModel by viewModels<DashBoardViewModel>()
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
                                    val uiState by dashBoardViewModel.uiState.collectAsStateWithLifecycle()
                                    DashBoardScreen(
                                        uiState = uiState,
                                        onRetry = { dashBoardViewModel.fetchDashboardData() },
                                        onCardClick = {
                                            backStack.add(Detail(it))
                                            dashBoardViewModel.getMovieDetail(it)
                                        }
                                    )
                                }

                                is Detail -> NavEntry(key = key) {
                                    val detailsUiState by dashBoardViewModel.detailsUiState.collectAsStateWithLifecycle()
                                    MovieDetailScreen(
                                        detailsUiState = detailsUiState,
                                        onRetry = { dashBoardViewModel.getMovieDetail(key.movieId) }
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
