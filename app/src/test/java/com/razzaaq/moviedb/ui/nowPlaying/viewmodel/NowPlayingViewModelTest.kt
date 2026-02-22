package com.razzaaq.moviedb.ui.nowPlaying.viewmodel

import com.razzaaq.moviedb.api.ApiService
import com.razzaaq.moviedb.api.dto.ConfigurationDetail
import com.razzaaq.moviedb.api.dto.MovieDetail
import com.razzaaq.moviedb.api.dto.NowPlayingDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class NowPlayingViewModelTest {

    private lateinit var viewModel: NowPlayingViewModel
    private val apiService = mock(ApiService::class.java)

    @Before
    fun setup() {
        // Use UnconfinedTestDispatcher for Main to ensure immediate execution of coroutines
        Dispatchers.setMain(UnconfinedTestDispatcher())
        
        // Provide default stubs to avoid NPEs during ViewModel initialization in the init block
        runBlocking {
            whenever(apiService.getTMDBConfiguration()).doReturn(ConfigurationDetail())
            whenever(apiService.getNowPlayingMovies()).doReturn(NowPlayingDto())
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should fetch configuration and now playing movies concurrently`() = runTest {
        // Given
        val mockConfig = ConfigurationDetail(images = ConfigurationDetail.Images(secureBaseUrl = "https://base.url", posterSizes = listOf("w500")))
        val mockNowPlaying = NowPlayingDto(results = listOf(NowPlayingDto.Result(id = 1, title = "Movie 1", posterPath = "/path")))

        whenever(apiService.getTMDBConfiguration()).doReturn(mockConfig)
        whenever(apiService.getNowPlayingMovies()).doReturn(mockNowPlaying)

        // When
        viewModel = NowPlayingViewModel(apiService)
        
        // Start collecting uiState to trigger WhileSubscribed and capture updates
        val job = backgroundScope.launch {
            viewModel.uiState.collect()
        }
        
        advanceUntilIdle()

        // Then
        val uiState = viewModel.uiState.value
        assertEquals(1, uiState.nowPlaying.size)
        assertEquals("Movie 1", uiState.nowPlaying[0].title)
        assertEquals("https://base.url", uiState.posterImage.url)
        
        job.cancel()
    }

    @Test
    fun `getMovieDetail should update detailsUiState`() = runTest {
        // Given
        val mockConfig = ConfigurationDetail(images = ConfigurationDetail.Images(secureBaseUrl = "https://base.url", posterSizes = listOf("w500")))
        val mockDetail = MovieDetail(id = 1, title = "Movie Detail")
        
        whenever(apiService.getTMDBConfiguration()).doReturn(mockConfig)
        whenever(apiService.getMovieDetail(1)).doReturn(mockDetail)

        viewModel = NowPlayingViewModel(apiService)
        
        // IMPORTANT: Collect in separate coroutines. 
        // A single launch block with two collects will hang on the first one.
        val job1 = backgroundScope.launch { viewModel.uiState.collect() }
        val job2 = backgroundScope.launch { viewModel.detailsUiState.collect() }
        
        advanceUntilIdle()

        // When
        viewModel.getMovieDetail(1)
        advanceUntilIdle()

        // Then
        val detailsState = viewModel.detailsUiState.value
        assertEquals(mockDetail, detailsState.nowPlayingDetails)
        assertEquals("https://base.url", detailsState.posterImage.url)
        
        job1.cancel()
        job2.cancel()
    }
}
