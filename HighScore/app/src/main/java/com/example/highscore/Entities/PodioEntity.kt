package com.example.highscore.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="podio_entity")
data class PodioEntity(
    //La primary key es la variable siguiente, en este caso, nombre.
    @PrimaryKey(autoGenerate = true)
    var userName:String = "",
    var numPartidas:Int=0,

    //maxPuntuación indica la diferencia entre lo que ha sacado el jugador y lo que ha sacado la máquina.
    var maxPuntuacion:Int=0
)
