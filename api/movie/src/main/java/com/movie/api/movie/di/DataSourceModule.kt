package com.movie.api.movie.di

import com.movie.api.movie.data.datasource.MovieRemoteDataSource
import com.movie.api.movie.data.datasource.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    internal abstract fun bindMovieRemoteDataSource(
        remote: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource
}