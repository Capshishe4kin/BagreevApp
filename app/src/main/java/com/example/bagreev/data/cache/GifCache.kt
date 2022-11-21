package com.example.bagreev.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif_table")
data class GifCache(
    @PrimaryKey
    @ColumnInfo(name = "link")
    val link: String
)