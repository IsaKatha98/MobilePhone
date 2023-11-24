package com.example.rentingroom.Views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.rentingroom.Entities.Habitaciones
import com.example.rentingroom.R
import com.example.rentingroom.Room.RentingDao
import com.example.rentingroom.ui.theme.RentingRoomTheme
import kotlinx.coroutines.runBlocking

/**
 * Función que muestra la lista de las habitaciones.
 */
@Composable
fun ListaHabitaciones (navController: NavController, dao: RentingDao, context: Context) {

    val habitaciones:List<Habitaciones> = runBlocking {
        dao.getHabitaciones()
    }.sortedBy { it.id }

    LazyColumn(modifier=Modifier.fillMaxSize()) {
        items(1){
            for (habitacion in habitaciones) {
                Column {

                    //Si tiene una fecha de entrada asignada, la mostramos en rojo.
                    if (habitacion.fechaEntrada>0&&habitacion.fechaSalida.equals(0)) {

                        Box (Modifier.background(color= Color.Red)){
                            //Imagen de la habitación
                            Image(painter = painterResource(id = R.drawable.cama), contentDescription = "cama")
                            Text("$habitacion.id")
                            Text("$habitacion.mensualidad")
                        }


                    //Si tiene una fecha de entrada igual a 0, la mostramos en gris y la hacemos clicable.
                    } else {

                        Box (Modifier.background(color= Color.Gray)
                            .clickable { /*TODO:nos llevaría a la vista de gestión*/ }){
                            //Imagen de la habitación
                            Image(painter = painterResource(id = R.drawable.cama), contentDescription = "cama")
                            Text("$habitacion.id")
                            Text("$habitacion.mensualidad")
                        }

                    }


                }
            }
        }
    }

}

