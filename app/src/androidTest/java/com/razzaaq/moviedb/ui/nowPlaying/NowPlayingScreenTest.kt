package com.razzaaq.moviedb.ui.nowPlaying

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.Image
import com.razzaaq.moviedb.ui.nowPlaying.viewmodel.Movie
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NowPlayingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun nowPlayingScreen_displaysMovies() {
        // Given
        val movies = listOf(
            Movie(id = 1, title = "Movie 1", posterPath = "/path1"),
            Movie(id = 2, title = "Movie 2", posterPath = "/path2")
        )
        val posterImage = Image(url = "https://base.url", imageSize = "w500")

        // When
        composeTestRule.setContent {
            NowPlayingScreen(
                movies = movies,
                posterImage = posterImage,
                onCardClick = {}
            )
        }

        // Then
        composeTestRule.onNodeWithText("Movie 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Movie 2").assertIsDisplayed()
    }

    @Test
    fun nowPlayingScreen_clickingMovie_triggersCallback() {
        // Given
        var clickedMovieId = -1
        val movies = listOf(Movie(id = 42, title = "Click Me", posterPath = "/path"))
        val posterImage = Image(url = "https://base.url", imageSize = "w500")

        composeTestRule.setContent {
            NowPlayingScreen(
                movies = movies,
                posterImage = posterImage,
                onCardClick = { clickedMovieId = it }
            )
        }

        // When
        composeTestRule.onNodeWithText("Click Me").performClick()

        // Then
        assertEquals(42, clickedMovieId)
    }
}
