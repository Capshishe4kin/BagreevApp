package com.example.bagreev.core

import android.app.Application
import androidx.room.Room
import com.example.bagreev.data.Repository
import com.example.bagreev.data.cache.GifDatabase

class App : Application() {

    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()

        val dao = Room.databaseBuilder(this, GifDatabase::class.java, "gif_database")
            .fallbackToDestructiveMigration()
            .build().gifDao()
        repository = Repository(dao)
    }
}