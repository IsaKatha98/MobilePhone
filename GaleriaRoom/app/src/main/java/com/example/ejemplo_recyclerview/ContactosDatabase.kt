package com.example.ejemplo_recyclerview

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contacto::class), version = 1)
abstract class ContactosDatabase: RoomDatabase() {
    abstract fun contactosDao(): ContactosDao
}