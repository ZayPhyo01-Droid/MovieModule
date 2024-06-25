package com.movie.ktor.util

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.movie.ktor.util.exception.DataException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerializationException

import java.net.UnknownHostException


suspend inline fun <reified R> eitherCall(
    apiCall: () -> HttpResponse,
): Either<DataException, R> {
    return try {
        val response = apiCall()
        val body = response.body<R>()
        body?.right() ?: DataException.Api("Data not found. Please try again.").left()
    } catch (e: Exception) {
        e.convertEither()
    }
}


fun Exception.convertEither(): Either<DataException, Nothing> = when (this) {
    is UnknownHostException -> Either.Left(DataException.Network)
    is SerializationException -> {
        Either.Left(
            DataException.Api(
                message = this.message ?: "Server response something went wrong",
                title = "Fail to fetch",
                errorCode = -1
            )
        )
    }

    else -> Either.Left(
        DataException.Api(
            message = this.message ?: "Something went wrong. Please try again.",
            title = "Fail to fetch",
            errorCode = -1
        )
    )
}