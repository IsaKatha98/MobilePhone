package com.example.highscore.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="podio_entity")
data class PodioEntity(
    //La primary key es la variable siguiente, en este caso, id.
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var userName:String = "",
    var numPartidas:Int=0,
    var maxPuntuacion:Int=0
)
