package com.razzaaq.moviedb.domain.usecase

import com.razzaaq.moviedb.data.mapper.MovieMappers
import com.razzaaq.moviedb.data.repository.MovieRepository
import com.razzaaq.moviedb.ui.model.DetailsUiState
import com.razzaaq.moviedb.util.NetworkResult
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MovieMappers
) {
    suspend operator fun invoke(movieId: Int): NetworkResult<DetailsUiState> {
        val configResult = repository.getTMDBConfiguration()
        if (configResult is NetworkResult.Error) return NetworkResult.Error(configResult.exception)
        
        val detailResult = repository.getMovieDetail(movieId)
        if (detailResult is NetworkResult.Error) return NetworkResult.Error(detailResult.exception)

        return if (configResult is NetworkResult.Success && detailResult is NetworkResult.Success) {
            with(mapper) {
                val posterImage = configResult.data.toImage()
                NetworkResult.Success(
                    DetailsUiState(
                        movieDetail = detailResult.data.toUi(posterImage),
                        posterImage = posterImage
                    )
                )
            }
        } else {
            NetworkResult.Loading
        }
    }
}
