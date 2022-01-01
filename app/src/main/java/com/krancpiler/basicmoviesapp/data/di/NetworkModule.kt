package com.krancpiler.basicmoviesapp.data.di

import com.krancpiler.basicmoviesapp.data.network.MoviesApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideMoviesApiKeyInterceptor(): MoviesApiKeyInterceptor = MoviesApiKeyInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, moviesApiKeyInterceptor: MoviesApiKeyInterceptor) = OkHttpClient.Builder()
        .addInterceptor(moviesApiKeyInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(com.krancpiler.basicmoviesapp.BuildConfig.BASE_URL)
        .client(okHttpClient)
        .build()

}