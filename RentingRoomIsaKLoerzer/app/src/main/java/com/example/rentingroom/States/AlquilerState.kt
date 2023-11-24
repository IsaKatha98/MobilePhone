package com.example.rentingroom.States

import com.example.rentingroom.Entities.Habitaciones

//Esta clase guarda la lista de habitaciones.
data class AlquilerState(
    val listHabitaciones: List<Habitaciones> = emptyList()

)
