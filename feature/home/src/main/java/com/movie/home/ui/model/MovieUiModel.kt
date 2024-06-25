package com.movie.home.ui.model

import com.movie.api.movie.domain.model.MovieModel

data class MovieUiModel(
    val trendingMovies: List<MovieModel>? = null,
    val nowPlayingMovies: List<MovieModel>? = null,
    val topRatedMovies: List<MovieModel>? = null,
    val popularMovies: List<MovieModel>? = null,
    val latestMovie: MovieModel? = null
)
