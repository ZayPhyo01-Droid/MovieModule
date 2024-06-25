package com.movie.api.movie.domain.usecase

import com.movie.api.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(movieId: String) = repository.getMovieDetail(movieId)
}