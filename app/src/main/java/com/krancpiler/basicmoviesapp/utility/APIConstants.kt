package com.krancpiler.basicmoviesapp.utility

object APIConstants {
    const val TRENDING_MOVIES = "trending/{media_type}/{time_window}"
    const val SEARCH_MOVIES = "search/movie"

    //    LOGIN
    const val CREATE_REQUEST_TOKEN = "authentication/token/new"
    const val CREATE_SESSION_LOGIN = "authentication/token/validate_with_login"
    const val CREATE_LASTING_SESSION = "authentication/session/new"
}