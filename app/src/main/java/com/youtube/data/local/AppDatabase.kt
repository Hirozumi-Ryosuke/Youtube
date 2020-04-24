package com.youtube.data.local

@Database(entities = [(Movies::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}