package com.movie.network

fun String.image() = if (this.isEmpty()) "" else "https://image.tmdb.org/t/p/w500/${
    if (this.first() == '/') this.drop(1) else this
}"

fun String.imageOriginal() = if (this.isEmpty()) "" else "https://image.tmdb.org/t/p/original/${
    if (this.first() == '/') this.drop(1) else this
}"