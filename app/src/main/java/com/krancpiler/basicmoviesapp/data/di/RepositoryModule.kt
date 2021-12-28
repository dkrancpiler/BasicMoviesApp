package com.krancpiler.basicmoviesapp.data.di

import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import com.krancpiler.basicmoviesapp.data.network.services.MoviesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMoviesRepository(moviesService: MoviesService) = MoviesRepository(moviesService)
}