package com.movie.api.movie.di

import com.movie.api.movie.domain.repository.MovieRepositoryImpl
import com.movie.api.movie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMovieRepository(
        repository: MovieRepositoryImpl
    ): MovieRepository
}