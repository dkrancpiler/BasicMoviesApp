package com.krancpiler.basicmoviesapp.data.di

import android.content.Context
import androidx.room.Room
import com.krancpiler.basicmoviesapp.data.storage.MoviesAppDatabase
import com.krancpiler.basicmoviesapp.data.storage.dao.UserDao
import com.krancpiler.basicmoviesapp.utility.RegularConstants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMoviesAppDatabase(@ApplicationContext appContext: Context): MoviesAppDatabase {
        return Room.databaseBuilder(appContext, MoviesAppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideUserDao(moviesAppDatabase: MoviesAppDatabase): UserDao = moviesAppDatabase.userDao()
}