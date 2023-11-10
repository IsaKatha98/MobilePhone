package com.example.ejemplo_recyclerview

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

class MisContactosApp: Application() {

    companion object {
        lateinit var database: ContactosDatabase
    }

    override fun onCreate() {
        super.onCreate()
        MisContactosApp.database= Room.databaseBuilder(this, ContactosDatabase::class.java,"contactos-db").build()
        }

    }





