package com.movie.api.movie.domain.repository

import arrow.core.Either
import com.movie.api.movie.data.datasource.MovieRemoteDataSource

import com.movie.api.movie.domain.model.MovieModel
import com.movie.ktor.util.exception.DataException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun getTrendingMovies(): Either<DataException, List<MovieModel>> {
        return movieRemoteDataSource.getTrendingMovies()
    }

    override suspend fun getNowPlayingMovies(): Either<DataException, List<MovieModel>> {
        return movieRemoteDataSource.getNowPlayingMovies()
    }

    override suspend fun getTopRatedMovies(): Either<DataException, List<MovieModel>> {
        return movieRemoteDataSource.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): Either<DataException, List<MovieModel>> {
        return movieRemoteDataSource.getPopularMovies()
    }

    override suspend fun getLatestMovies(): Either<DataException, MovieModel?> {
        return movieRemoteDataSource.getLatestMovies()
    }

    override suspend fun getUpcomingMovies(): Either<DataException, List<MovieModel>> {
        return movieRemoteDataSource.getUpcomingMovies()
    }

    override suspend fun getMovieDetail(movieId: String): Either<DataException, MovieModel> {
        return movieRemoteDataSource.getMovieDetail(movieId)
    }
}