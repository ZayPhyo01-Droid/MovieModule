package com.movie.api.movie.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestMovieResponse(
    val id: Int,
    val adult: Boolean?,
    @SerialName("poster_path")
    val posterPath: String?,
    val title: String?,
    val overview: String?,
)