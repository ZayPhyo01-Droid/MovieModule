package com.movie.api.movie.data.service

import arrow.core.Either

import com.movie.api.movie.data.model.response.MovieResponse
import com.movie.api.movie.data.model.response.MovieResultResponse
import com.movie.network.exception.DataException

interface MovieService {

    suspend fun getTrendingMovies(): Either<DataException, MovieResponse>

    suspend fun getNowPlayingMovies(): Either<DataException, MovieResponse>

    suspend fun getTopRatedMovies(): Either<DataException, MovieResponse>

    suspend fun getPopularMovies(): Either<DataException, MovieResponse>

    suspend fun getLatestMovies(): Either<DataException, MovieResponse?>

    suspend fun getUpcomingMovies(): Either<DataException, MovieResponse>

    suspend fun getMovieDetail(movieId: String): Either<DataException, MovieResultResponse>
}