package com.iesnervion.rentingroom.crud

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AlquilerDAO {
    // Todos las habitaciones
    @Query("SELECT * FROM tAlquiler")
    suspend fun listaAlquileres(): MutableList<AlquilerEntity>

    // Extrae una habitación
    @Query("SELECT * FROM tAlquiler where id=:id")
    suspend fun verAlquiler(id: Long): AlquilerEntity?

    // Cuenta cuántas habitaciones hay
    @Query("SELECT count(id) FROM tAlquiler")
    suspend fun numHabitaciones(): Long

    // Devuelve la última fecha
    @Query("SELECT Max(fechaEntrada) FROM tAlquiler")
    suspend fun ultimoDia(): Long

    // Devuelve el último cliente
    @Query("SELECT Max(idCliente) FROM tAlquiler")
    suspend fun ultimoCliente(): Long

    // Inserta el alquiler de una habitación que no existe o la crea vacía
    @Insert
    suspend fun insertaCliente(alquiler: AlquilerEntity): Long

    // Actualiza el alquiler de una habitación vacía existente
    @Update
    suspend fun actualizaCliente(alquiler: AlquilerEntity): Int
}
