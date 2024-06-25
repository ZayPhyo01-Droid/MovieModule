package com.movie.api.movie.data.service

import arrow.core.Either
import com.movie.api.movie.data.model.response.MovieResponse
import com.movie.api.movie.data.model.response.MovieResultResponse
import com.movie.network.eitherCall
import com.movie.network.exception.DataException
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class MovieServiceImpl @Inject constructor(private val httpClient: HttpClient) : MovieService {
    override suspend fun getTrendingMovies(): Either<DataException, MovieResponse> {
        return eitherCall {
            httpClient.get(URL.PATH_TRENDING)
        }
    }

    override suspend fun getNowPlayingMovies(): Either<DataException, MovieResponse> {
        return eitherCall {
            httpClient.get(URL.PATH_NOW_PLAYING)
        }
    }

    override suspend fun getTopRatedMovies(): Either<DataException, MovieResponse> {
        return eitherCall {
            httpClient.get(URL.PATH_TOP_RATED)
        }
    }

    override suspend fun getPopularMovies(): Either<DataException, MovieResponse> {
        return eitherCall {
            httpClient.get(URL.PATH_POPULAR)
        }
    }

    override suspend fun getLatestMovies(): Either<DataException, MovieResponse?> {
        return eitherCall {
            httpClient.get(URL.PATH_TRENDING)
        }
    }

    override suspend fun getUpcomingMovies(): Either<DataException, MovieResponse> {
        return eitherCall {
            httpClient.get(URL.PATH_UPCOMING)
        }
    }

    override suspend fun getMovieDetail(movieId: String): Either<DataException, MovieResultResponse> {
        return eitherCall {
            httpClient.get(
                URL.PATH_MOVIE_DETAIL + movieId
            )
        }
    }
}
