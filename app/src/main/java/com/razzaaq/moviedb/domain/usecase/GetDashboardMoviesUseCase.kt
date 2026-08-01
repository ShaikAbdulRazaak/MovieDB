package com.razzaaq.moviedb.domain.usecase

import com.razzaaq.moviedb.data.mapper.MovieMappers
import com.razzaaq.moviedb.data.repository.MovieRepository
import com.razzaaq.moviedb.ui.model.UiState
import com.razzaaq.moviedb.util.NetworkResult
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetDashboardMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMappers
) {
    suspend operator fun invoke(): NetworkResult<UiState> = coroutineScope {
        val configResult = repository.getTMDBConfiguration()
        if (configResult is NetworkResult.Error) return@coroutineScope NetworkResult.Error(configResult.exception)
        if (configResult is NetworkResult.Loading) return@coroutineScope NetworkResult.Loading

        val posterImage = with(mapper) {
            (configResult as NetworkResult.Success).data.toImage()
        }

        val nowPlayingDeferred = async { repository.getNowPlayingMovies() }
        val popularDeferred = async { repository.getPopular() }
        val topRatedDeferred = async { repository.getTopRated() }
        val upcomingDeferred = async { repository.getUpcomingMovies() }

        val nowPlaying = nowPlayingDeferred.await()
        val popular = popularDeferred.await()
        val topRated = topRatedDeferred.await()
        val upcoming = upcomingDeferred.await()

        if (nowPlaying is NetworkResult.Success &&
            popular is NetworkResult.Success &&
            topRated is NetworkResult.Success &&
            upcoming is NetworkResult.Success
        ) {
            with(mapper) {
                NetworkResult.Success(
                    UiState(
                        nowPlaying = nowPlaying.data.results.map { it.toUi(posterImage) },
                        popular = popular.data.results.map { it.toUi(posterImage) },
                        topRated = topRated.data.results.map { it.toUi(posterImage) },
                        upComing = upcoming.data.results.map { it.toUi(posterImage) }
                    )
                )
            }
        } else {
            // Collect first error encountered
            val error = listOf(nowPlaying, popular, topRated, upcoming)
                .filterIsInstance<NetworkResult.Error>()
                .firstOrNull()?.exception ?: Exception("Unknown error")
            NetworkResult.Error(error)
        }
    }
}
