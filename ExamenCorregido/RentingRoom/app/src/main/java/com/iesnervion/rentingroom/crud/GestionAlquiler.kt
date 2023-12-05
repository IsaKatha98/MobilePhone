package com.iesnervion.rentingroom.crud

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.iesnervion.rentingroom.MainActivity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class GestionAlquiler : ComponentActivity() {

    /**
     * Checkin utiliza los datos del alquiler de una habitación vacía o no existente.
     * Como la aplicación es muy básica, el tiempo será un INT y cada día será un número más.
     * Cada cliente entrará un día diferente con lo que coincidirán id con idCliente en la BD.
     */
    suspend fun checkin(context: Context, hab: AlquilerEntity) {
        var ultimoCliente: Long = 0     // Voy a comprobar cuál es el último cliente que ha entrada
        var ultimoDia: Long = 0         // Voy a comprobar cúando se ha entrado por última vez
        try {
            ultimoDia = MainActivity.database.tAlquilerDAO().ultimoDia()    //Busco el último día introducido
        } catch (e: Exception) {
            Toast.makeText(context, "Base de datos vacía", Toast.LENGTH_SHORT).show()   // Si da error es porque la BD está vacía
        }
        try {
            ultimoCliente = MainActivity.database.tAlquilerDAO().ultimoCliente()    // Busco el último cliente introducido
        } catch (e: Exception) {
            Toast.makeText(context, "No hay clientes aún", Toast.LENGTH_SHORT).show()   // Si da error es porque la BD está vacía
        }
        hab.idCliente = ultimoCliente + 1       // Incremento el id para el cliente nuevo
        hab.fechaEntrada = ultimoDia + 1        // Incremento el último día
        hab.mensualidad = MainActivity.precio   // Actualiza el precio de la habitación

        // Si existe id en la habitación (habitación existente) ...
        if (hab.id > 0)
            MainActivity.database.tAlquilerDAO().actualizaCliente(hab)      // ... se actualiza el estado de esa habitación
        else
            MainActivity.database.tAlquilerDAO().insertaCliente(hab)        // ... se introduce una nueva habitación
    }

    /**
     * Se realiza el checkout de una habitación ocupada.
     * Busco la habitación, inicializo sus valores a 0 (vacío la habitación) y actualizo en la BD.
     */
    suspend fun checkout(idHab: Long) {
        val ga = GestionAlquiler()
        val hab: AlquilerEntity = ga.verHabitacion(idHab)
        hab.idCliente = 0
        hab.fechaEntrada = 0
        hab.mensualidad = MainActivity.precio
        MainActivity.database.tAlquilerDAO().actualizaCliente(hab)  // Actualizo en la BD
    }

    /**
     * Obtengo la lista de alquileres en la BD.
     * No voy a necesitar esta función porque me limitaré a las habitaciones especificadas en la configuración.
     * No podré llevar un historial de ocupaciones (tampoco se especifica)
     */
    suspend fun getAlquileres(): MutableList<AlquilerEntity> {       // Corrutina que saca de la base de datos la lista de alquileres
        return MainActivity.database.tAlquilerDAO()
            .listaAlquileres()    // Se carga la lista de alquileres
    }

    /**
     * Si no existe la habitación (id no existe), se crea un nuevo registro con los valores inicializados (puede hacerse por BD)
     */
    private suspend fun creaHabitacion(): AlquilerEntity {
        val nuevoAlquiler = AlquilerEntity(
            idCliente = 0,
            fechaEntrada = 0,
            mensualidad = MainActivity.precio
        )
        MainActivity.database.tAlquilerDAO().insertaCliente(nuevoAlquiler)  // Inserto en la BD
        return nuevoAlquiler
    }

    /**
     * Obtengo los datos de UNA habitación
     */
    fun verHabitacion(idHab: Long): AlquilerEntity {
        // Por si no existiera, preparo un alquiler vacío para inicializar (aunque puedo ponerlo a NULL)
        var alquiler = AlquilerEntity(
            id = 0,
            idCliente = 0,
            fechaEntrada = 0,
            mensualidad = MainActivity.precio
        )
        // ALQUILER será una habitación en concreto (1) o una nueva (2)
        runBlocking {
            launch {
                alquiler = try {
                    MainActivity.database.tAlquilerDAO().verAlquiler(idHab)!!   // (1)
                } catch (e: Exception) {
                    creaHabitacion()            // (2)
                }
            }
        }
        return alquiler
    }

    /**
     * Devuelve la lista de habitaciones mediante un bucle.
     * Este bucle buscará en la base de datos para extraer o crear las habitaciones hasta el máximo especificado.
     */
    fun listaAlquileres(): MutableList<AlquilerEntity> {
        val listaHabitaciones: MutableList<AlquilerEntity> = ArrayList()
        for (num in 1..MainActivity.numHabitaciones) {
            runBlocking {
                launch {
                    listaHabitaciones.add(          // Añade a la lista ...
                        verHabitacion(num.toLong()) // ... la habitación extraída
                    )
                }
            }
        }
        return listaHabitaciones        // Devuelve la lista
    }
}