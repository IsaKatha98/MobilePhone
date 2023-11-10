package com.example.highscore

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.highscore.Entities.PodioEntity


//Esta es la parte que hace las veces de API, es decir, permite interactuar con la base de datos.
@Dao
interface PodioDao {

    //suspend indica que son funciones que necesitan runBlocking(multitarea).

    // Función que devuelve todas las tareas de la base de datos en una lista Mutable.
    @Query("SELECT * FROM podio_entity")
    suspend fun getAllPodio(): MutableList<PodioEntity>

    // Función que añade una partida, la que se pasa por parámetro, y devuelve el id insertado.
    // Devuelve Long porque la cantidad de datos guardada puede ser muy alto.
    //En caso de que se genere una partida con un id que ya existe, lo ignorará
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPodio(podioEntity : PodioEntity):Long

    // Función que busca un registro concreto por id (debe ser Long, no Int),
    //devuelve una entidad

    @Query("SELECT * FROM podio_entity where userName like :userName")
    suspend fun getPodioById(userName: String): PodioEntity

    // Función que actualiza un registro del podio
    //devuelve un booleano de confirmación
    @Update
    suspend fun updatePodio (podio: PodioEntity):Boolean

    //Función que elimina un registro del podio,
    //devuelve un booleano de confirmación.
    @Delete
    suspend fun deletePodio (podio: PodioEntity):Boolean
}