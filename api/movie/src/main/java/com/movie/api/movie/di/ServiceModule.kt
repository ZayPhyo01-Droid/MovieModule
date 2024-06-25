package com.movie.api.movie.di

import com.movie.api.movie.data.service.MovieService
import com.movie.api.movie.data.service.MovieServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {

    @Binds
    @Singleton
    internal abstract fun bindServiceModule(
        movieService: MovieServiceImpl
    ): MovieService
}