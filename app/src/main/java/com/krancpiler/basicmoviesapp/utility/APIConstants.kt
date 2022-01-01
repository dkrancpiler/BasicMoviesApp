package com.krancpiler.basicmoviesapp.utility

object APIConstants {
    //    MOVIES
    const val TRENDING_MOVIES = "trending/{media_type}/{time_window}"
    const val SEARCH_MOVIES = "search/movie"
    const val SINGLE_MOVIE = "movie/{movie_id}"

    //    LOGIN
    const val CREATE_REQUEST_TOKEN = "authentication/token/new"
    const val CREATE_SESSION_LOGIN = "authentication/token/validate_with_login"
    const val CREATE_LASTING_SESSION = "authentication/session/new"
    const val CREATE_GUEST_SESSION = "authentication/guest_session/new"
}