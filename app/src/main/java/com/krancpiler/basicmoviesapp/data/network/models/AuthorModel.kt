package com.krancpiler.basicmoviesapp.data.network.models

data class AuthorModel(
    val name: String,
    val username: String,
    val avatar_path: String,
    val rating: Double?
)
