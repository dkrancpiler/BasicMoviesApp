package com.krancpiler.basicmoviesapp.data.network.models

data class ReviewModel(
    val author:String,
    val author_details: AuthorModel,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)
