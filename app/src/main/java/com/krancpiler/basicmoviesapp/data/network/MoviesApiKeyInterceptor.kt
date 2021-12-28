package com.krancpiler.basicmoviesapp.data.network

import com.krancpiler.basicmoviesapp.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class MoviesApiKeyInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val originalUrl: HttpUrl = request.url
        val url: HttpUrl = originalUrl.newBuilder()
            .addQueryParameter("api_key", BuildConfig.MOVIES_API_KEY)
            .build()
        val requestBuilder: Request.Builder = request.newBuilder()
            .url(url)
        return chain.proceed(requestBuilder.build())
    }
}