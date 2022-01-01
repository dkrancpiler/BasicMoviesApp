package com.krancpiler.basicmoviesapp.data.network.models

data class MovieModel(
    val backdrop_path:String,
    val original_language:String,
    val original_title:String,
    val poster_path:String?,
    val title:String,
    val overview:String,
    val release_date:String,
    val mediaType:String,
    val vote_count:Int,
    val id:Int,
    val vote_average:Float,
    val popularity:Float,
    val adult:Boolean,
    val video:Boolean,
    val genre_ids:ArrayList<Int>
)
