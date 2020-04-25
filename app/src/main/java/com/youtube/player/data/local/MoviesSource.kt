package com.youtube.player.data.local

data class MoviesSource(
    @SerializedName("status") var status: Int,
    @SerializedName("source") var source: String = "",
    @SerializedName("movies") var movies: List<Movies> = emptyList())