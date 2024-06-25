package com.movie.network

import android.content.Context
import android.net.http.HttpResponseCache.install
import com.chuckerteam.chucker.api.ChuckerInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttp

object KtorHelper {
    fun createHttpClient(context: Context) = HttpClient(io.ktor.client.engine.okhttp.OkHttp) {
        engine {
            config {
                addInterceptor(ChuckerInterceptor(context))
            }
        }
        defaultRequest {
            header(
                HttpHeaders.Authorization,
                "Bearer YOUR API KEY"
            )
            url("https://api.themoviedb.org/3/")
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
}
