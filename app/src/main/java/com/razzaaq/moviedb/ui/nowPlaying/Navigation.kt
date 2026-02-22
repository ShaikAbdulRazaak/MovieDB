package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

data object NowPlaying
data class NowPlayingDetail(val movieId: String)

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val backStack = remember { mutableStateListOf<Any>(NowPlaying) }
    backStack.add(NowPlayingDetail("movieId"))
    backStack.removeLastOrNull()
}
