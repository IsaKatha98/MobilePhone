package com.iesnervion.rentingroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


/**
 * Bloque con el diseño general
 */
@Composable
fun Pantalla1(
    navController: NavController,   // Controlador de navegación
    modifier: Modifier = Modifier,  // Modificador de estilo
) {
    MuestraConfiguracion(modifier)  // Muestro la configuración de las variables globales
    BarraInferior(navController, modifier)   // Muestro la barra inferior
}

/**
 * Muestro la configuración de forma sencilla: una columna, con dos filas para organizar 4 textos
 */
@Composable
fun MuestraConfiguracion(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.padding(15.dp)
        ) {
            Text(text = "Núm. habitaciones:")
            Text(text = MainActivity.numHabitaciones.toString())
        }
        Row {
            Text(text = "Precio/hab.:")
            Text(text = MainActivity.precio.toString() + " €")
        }
        Spacer(modifier = modifier)
    }
}
