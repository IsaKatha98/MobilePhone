package com.iesnervion.rentingroom.crud

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iesnervion.rentingroom.MainActivity

@Entity(tableName = "tAlquiler")
data class AlquilerEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var idCliente: Long = 0,
    var fechaEntrada: Long = 0,
    var mensualidad: Float = MainActivity.precio
)
