package com.youtube.player.di.modules

import android.app.Application
import player.data.local.AppDatabase
import player.data.remote.WebService
import player.di.scopes.AppScope

@Module
class AppComponent {

    @Provides
    @AppScope
    fun provideRetrofitClient(retrofit: Retrofit) = retrofit.create(WebService::class.java)!!

    @Provides
    @AppScope
    fun provideRoomDB(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "movies-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @AppScope
    fun provideMoviesDao(appDatabase: AppDatabase) = appDatabase.moviesDao()

}