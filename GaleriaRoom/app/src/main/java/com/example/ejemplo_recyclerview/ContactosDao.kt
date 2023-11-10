package com.example.ejemplo_recyclerview

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactosDao {

    @Query("SELECT * FROM contacto_entity")
    suspend fun getAllContactos(): MutableList< Contacto>  // Función que devuelve todas las tareas de la base de datos en una lista Mutable.

    @Insert
    suspend fun addContacto(contacto: Contacto):Long    // Función que añade una tarea, la que se pasa por parámetro, y devuelve el id insertado.                                                          // Devuelve Long porque la cantidad de datos guardada puede ser muy alto.

    @Query("SELECT * FROM contacto_entity where name like :name")
    suspend fun getContactobyName(name: String): Contacto        // Función que busca tareas por id (debe ser Long, no Int)

    @Update
    suspend fun updateContacto(contacto:Contacto):Int     // Función que actualiza una tarea y devuelve

    @Delete
    suspend fun deleteTask(contacto: Contacto):Int
}