package com.movie.upcoming.domain.usecase

import com.movie.api.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetUpcomingMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke() = repository.getUpcomingMovies()
}