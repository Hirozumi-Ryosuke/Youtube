package com.youtube.player.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.youtube.player.base.repo.BaseDao

@Dao
interface MoviesDao : BaseDao<Movies> {

    /**
     * Insert movies into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movies>): List<Long>

    /**
     * Get all the movies from database
     */
    @Query("SELECT * FROM Movies")
    fun getMovies(): LiveData<List<Movies>>


    /**
     * Get the movie details on given id
     */
    @Query("SELECT * FROM Movies WHERE id=:movieId")
    fun getMoviesDetails(movieId: Int): LiveData<Movies>
}