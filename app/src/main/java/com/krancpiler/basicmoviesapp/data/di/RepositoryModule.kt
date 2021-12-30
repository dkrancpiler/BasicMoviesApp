package com.krancpiler.basicmoviesapp.data.di

import com.krancpiler.basicmoviesapp.data.network.repo.AuthRepository
import com.krancpiler.basicmoviesapp.data.network.repo.MoviesRepository
import com.krancpiler.basicmoviesapp.data.network.services.AuthService
import com.krancpiler.basicmoviesapp.data.network.services.MoviesService
import com.krancpiler.basicmoviesapp.data.storage.dao.UserDao
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

    @Singleton
    @Provides
    fun provideAuthRepository(authService: AuthService, userDao: UserDao) = AuthRepository(authService, userDao)
}