package com.example.rentingroom.Room

import androidx.room.Query
import androidx.room.Update
import com.example.rentingroom.Entities.Habitaciones

interface RentingDao {

    @Query ("Select * from habitaciones")
    fun getHabitaciones(): List <Habitaciones> //Función que devuelve una lista con todos los alquileres.

    @Query("Select * from habitaciones where id= :id")
    suspend fun getAlquilerbyId(id:Long):Habitaciones //Función que devuelve una habitación según su Id.

    @Update
    suspend fun updateAlquiler(habitacion: Habitaciones) //Función que actualiza un alquiler.
}