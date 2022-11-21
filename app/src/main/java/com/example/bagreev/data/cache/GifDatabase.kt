package com.example.bagreev.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GifCache::class], version = 1)
abstract class GifDatabase : RoomDatabase() {

    abstract fun gifDao() : GifDao
}