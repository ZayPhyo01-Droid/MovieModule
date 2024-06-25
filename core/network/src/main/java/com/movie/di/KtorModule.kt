package com.movie.di

import android.content.Context
import com.movie.network.KtorHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context : Context
    ) = KtorHelper.createHttpClient(context)
}