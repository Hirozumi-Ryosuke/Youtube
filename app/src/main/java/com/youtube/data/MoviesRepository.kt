package com.youtube.data

import androidx.lifecycle.LiveData
import com.youtube.data.local.Movies
import com.youtube.data.local.MoviesDao
import com.youtube.data.local.MoviesSource
import com.youtube.data.remote.WebService

class MoviesRepository @Inject constructor(val moviesDao: MoviesDao,
                                           val webService: WebService
) {

    val appExecutors: AppExecutors = AppExecutors()
    /**
     * Fetch the news movies from database if exist else fetch from web
     * and persist them in the database
     */
    fun getMoviesTrailers(): LiveData<Resource<List<Movies>>> {
        return object : NetworkBoundResource<List<Movies>, MoviesSource>(appExecutors) {
            override fun saveCallResult(item: MoviesSource) {
                moviesDao.insertMovies(item.movies)
            }

            override fun shouldFetch(data: List<Movies>?) = true

            override fun loadFromDb() = moviesDao.getMovies()

            override fun createCall() = webService.getMovies()
        }.asLiveData()
    }

    fun getMoviesDetails(movieId: Int): LiveData<Resource<Movies>> {
        return object : NetworkBoundResource<Movies, MoviesSource>(appExecutors) {
            override fun saveCallResult(item: MoviesSource) {
                // moviesDao.insertMovies(item.movies)
            }

            override fun shouldFetch(data: Movies?) = false

            override fun loadFromDb() = moviesDao.getMoviesDetails(movieId)

            override fun createCall() = webService.getMovies()
        }.asLiveData()
    }

}