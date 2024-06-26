package com.movie.api.movie.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MovieResponse(
    val results: List<MovieResultResponse>?,
)