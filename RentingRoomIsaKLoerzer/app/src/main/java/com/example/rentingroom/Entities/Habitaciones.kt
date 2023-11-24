package com.example.rentingroom.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat

@Entity(tableName="habitaciones")
data class Habitaciones(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0,
    val fechaEntrada: Long=0,
    val fechaSalida: Long= 0,
    val idCliente:Int=0,
    val mensualidad:DecimalFormat
)
