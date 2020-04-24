package com.youtube.data.remote

import androidx.lifecycle.LiveData
import com.youtube.data.local.MoviesSource

interface WebService {
    @GET("movieslist")
    fun getMovies(): LiveData<ApiResponse<MoviesSource>>
}