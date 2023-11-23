package com.example.roomjetpack.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomjetpack.Entities.Users

@Database(entities = [Users::class],
    version=1,
    exportSchema = false)

//La clase debe ser abstracta e implementar RoomDatabase
abstract class UsersDatabase:RoomDatabase() {

    abstract fun usersDAO(): UsersDao
}