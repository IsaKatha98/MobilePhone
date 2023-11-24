package com.example.rentingroom.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat

//No tengo muy claro que esto deba ser una tabla pero bueno, porque
//podría meterse como atributos val en la actividad.
@Entity(tableName="configuracion")
data class Configuracion(
    @PrimaryKey(autoGenerate = true)
    val idConfiguracion: Int=0, //se generará un id de la configuraión y será la PK de la entidad.
    val numHabitaciones:Int=0,
    val precio:DecimalFormat
)
