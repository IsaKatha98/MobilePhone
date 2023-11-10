package com.example.highscore

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.highscore.Entities.PodioEntity

@Database(entities = arrayOf(PodioEntity::class), version = 1)
abstract class PodioDatabase : RoomDatabase() {
    abstract fun podioDao(): PodioDao
}