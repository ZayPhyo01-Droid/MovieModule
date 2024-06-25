package com.movie.api.movie.data.mapper

import com.common.extension.orFalse
import com.common.extension.orZero
import com.movie.api.movie.data.model.response.MovieResponse
import com.movie.api.movie.data.model.response.MovieResultResponse

internal fun MovieResponse.toMovieModels() = results.map {
    it.toModel()
}

internal fun MovieResultResponse.toModel() = com.movie.api.movie.domain.model.MovieModel(
    id = this.id,
    title = this.title.orEmpty(),
    posterPath = this.posterPath.orEmpty(),
    releaseDate = this.releaseDate.orEmpty(),
    voteAverage = this.voteAverage.orZero(),
    voteCount = this.voteCount.orZero(),
    overview = this.overview.orEmpty(),
    popularity = this.popularity.orZero(),
    originalLanguage = this.originalLanguage.orEmpty(),
    originalTitle = this.originalTitle.orEmpty(),
    backdropPath = this.backdropPath.orEmpty(),
    adult = this.adult.orFalse(),
    video = this.video.orFalse()
)

