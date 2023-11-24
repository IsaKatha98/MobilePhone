package com.example.rentingroom.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rentingroom.Entities.Habitaciones
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities=[Habitaciones::class],
    version=1,
    exportSchema=false)

//Clase abstracta que hereda de RoomDatabase
abstract class RentingDatabase:RoomDatabase() {
    abstract fun RentingDao():RentingDao

}