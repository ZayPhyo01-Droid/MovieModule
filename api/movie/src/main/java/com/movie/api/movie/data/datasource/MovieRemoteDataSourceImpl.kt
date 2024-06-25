package com.movie.api.movie.data.datasource

import arrow.core.Either
import com.movie.api.movie.data.mapper.toModel
import com.movie.api.movie.data.mapper.toMovieModels
import com.movie.api.movie.data.service.MovieService
import com.movie.api.movie.domain.model.MovieModel
import com.movie.network.exception.DataException
import javax.inject.Inject


internal class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override suspend fun getTrendingMovies(): Either<DataException, List<MovieModel>> {
        return movieService.getTrendingMovies().map {
            it.toMovieModels()
        }
    }

    override suspend fun getNowPlayingMovies():
            Either<DataException, List<MovieModel>> {
        return movieService.getNowPlayingMovies().map {
            it.toMovieModels()
        }
    }

    override suspend fun getTopRatedMovies(): Either<DataException, List<MovieModel>> {
        return movieService.getTopRatedMovies().map {
            it.toMovieModels()
        }
    }

    override suspend fun getPopularMovies(): Either<DataException, List<MovieModel>> {
        return movieService.getPopularMovies().map {
            it.toMovieModels()
        }
    }

    override suspend fun getLatestMovies(): Either<DataException, MovieModel?> {
        return movieService.getLatestMovies().map {
            if (it?.results?.firstOrNull() == null) null else it.results.first().toModel()
        }
    }

    override suspend fun getUpcomingMovies(): Either<DataException, List<MovieModel>> {
        return movieService.getUpcomingMovies().map {
            it.toMovieModels()
        }
    }

    override suspend fun getMovieDetail(movieId: String): Either<DataException, MovieModel> {
        return movieService.getMovieDetail(movieId).map {
            it.toModel()
        }
    }
}