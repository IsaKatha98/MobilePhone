package com.example.roomjetpack.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val userName:String="",
    val password:String=""
)
