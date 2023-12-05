package com.iesnervion.rentingroom

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.iesnervion.rentingroom.crud.AlquilerEntity
import com.iesnervion.rentingroom.crud.GestionAlquiler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Bloque con el diseño general
 */
@Composable
fun Pantalla3(navController: NavController, idHab: Long) {
    val modifier = Modifier.fillMaxSize()
    val ga = GestionAlquiler()
    Detalle(modifier = modifier, hab = ga.verHabitacion(idHab), navController)     // Paso al composable, la habitación seleccionada en la pantalla 2
    BarraInferior(navController, modifier)   // Muestro la barra inferior
}

/**
 * Composable mostrando el detalle de la habitación. Una columna con 4 textos y un botón
 */
@Composable
fun Detalle(modifier: Modifier, hab: AlquilerEntity, navController: NavController) {
    val context: Context = LocalContext.current

    Card(
        modifier = modifier
    ) {
        // La columna distribuye 4 textos de forma apilada con los datos del alquiler
        Column(
            modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = hab.id.toString(),
                fontSize = 48.sp,
            )
            Text(
                text = "Cliente  " + hab.idCliente, fontSize = 24.sp,
            )
            Text(
                text = "Entró el  " + hab.fechaEntrada, fontSize = 18.sp,
            )
            Text(
                text = "Cuota " + hab.mensualidad, fontSize = 18.sp,
            )

            // El botón se mostrará verde si la habitación esta vacía y rojo si está ocupada
            // Además mostrará Checkin o Checkout según exista o no cliente dentro
            val texto: String
            val color: Color
            if (hab.idCliente > 0) {
                texto = "Check out"
                color = Color.Red
            } else {
                texto = "Check in"
                color = Color.Green
            }
            Button(
                onClick = {
                    val ga = GestionAlquiler()
                    if (hab.idCliente > 0) {
                        navController.navigate("lista")
                        runBlocking { launch { ga.checkout(hab.id) }
                       }
                    } else {
                        navController.navigate("lista")
                        runBlocking { launch { ga.checkin(context, hab) } }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    contentColor = Color.White
                )

            ) {
                Text(
                    text = texto,
                    fontSize = 14.sp
                )
            }
        }
    }
}
