package com.example.bagreev.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GifDao {

    @Query("SELECT * FROM gif_table")
    fun readAllData(): List<GifCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gifCache: GifCache)
}