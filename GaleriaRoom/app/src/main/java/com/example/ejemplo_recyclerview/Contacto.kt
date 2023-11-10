package com.example.ejemplo_recyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="contacto_entity")
data class Contacto (@PrimaryKey (autoGenerate = false)
    var name: String="",
    var tlf:String="",
    var gender:String="")