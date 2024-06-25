package com.movie.api.movie.domain.usecase

import com.movie.api.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetTrendingMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke() = repository.getTrendingMovies()
}