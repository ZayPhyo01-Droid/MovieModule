package com.movie.ktor.util.exception

import arrow.core.Option

sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    object Network : DataException("Unable to connect. Please check connection.")



    data class Api(
        override val message: String,
        val title: String = "",
        val errorCode: Int = -1
    ) : DataException(message)
}