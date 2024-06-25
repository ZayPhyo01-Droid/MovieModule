package com.movie.ktor.util.handler

import arrow.core.Either
import arrow.core.Some
import com.movie.ktor.util.exception.DataException


fun <A : DataException, B> Either<A, B>.handle(
    left: (DataException) -> Unit = {},
    right: (B) -> Unit = {},
    terminate: () -> Unit = {}
) {
    this.tapLeft {
        left(it)
        terminate()
    }.tap {
        right.invoke(it)
        terminate()
    }
}

