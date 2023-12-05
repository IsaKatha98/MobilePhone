package com.iesnervion.rentingroom.crud

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AlquilerEntity::class], version = 1)
abstract class AlquilerDatabase : RoomDatabase() {
    abstract fun tAlquilerDAO(): AlquilerDAO
}