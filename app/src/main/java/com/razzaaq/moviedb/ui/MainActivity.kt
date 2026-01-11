package com.razzaaq.moviedb.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.razzaaq.moviedb.ui.nowPlaying.NowPlayingScreen
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.NowPlayingViewModel
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
                    NowPlayingScreen(
                        modifier = Modifier.padding(innerPadding),
                        state,
                        configuration
                    )
                }
            }
        }
    }
}