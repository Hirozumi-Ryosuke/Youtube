package com.youtube.player.data.remote

import androidx.lifecycle.LiveData
import player.data.local.MoviesSource

interface WebService {
    @GET("movieslist")
    fun getMovies(): LiveData<ApiResponse<MoviesSource>>
}