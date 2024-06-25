package com.movie.api.movie.domain.repository

import arrow.core.Either

import com.movie.api.movie.domain.model.MovieModel
import com.movie.network.exception.DataException

interface MovieRepository {
    suspend fun getTrendingMovies(): Either<DataException, List<MovieModel>>
    suspend fun getNowPlayingMovies(): Either<DataException, List<MovieModel>>
    suspend fun getTopRatedMovies(): Either<DataException, List<MovieModel>>
    suspend fun getPopularMovies(): Either<DataException, List<MovieModel>>
    suspend fun getLatestMovies(): Either<DataException, MovieModel?>
    suspend fun getUpcomingMovies(): Either<DataException, List<MovieModel>>
    suspend fun getMovieDetail(movieId: String): Either<DataException, MovieModel>
}