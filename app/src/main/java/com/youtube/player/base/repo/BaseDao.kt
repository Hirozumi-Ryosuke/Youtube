package com.youtube.player.base.repo

import androidx.room.*

@Dao
interface BaseDao<in T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: T)

    @Delete
    fun delete(vararg data: T)

    @Update
    fun update(vararg data: T)
}