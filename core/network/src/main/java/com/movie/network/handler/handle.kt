package com.movie.network.handler

import arrow.core.Either
import com.movie.network.exception.DataException


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

