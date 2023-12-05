package com.iesnervion.rentingroom

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
fun Pantalla2(
    navController: NavController,   // Controlador de navegación
    modifier: Modifier = Modifier,  // Modificador de estilo
) {
    val ga = GestionAlquiler()      // Gestor de alquileres
    Lista(
        itemHabitacion = ga.listaAlquileres(),  // Paso al composable Lista, las habitaciones del programa
        navController                           // y el controlador de navegación para la pantalla 3
    )
    BarraInferior(navController, modifier)   // Muestro la barra inferior
}

/**
 * Lista de habitaciones
 */
@Composable
fun Lista(
    itemHabitacion: List<AlquilerEntity>,   // Lista de habitaciones
    navController: NavController            // Controlador de navegación
) {
    LazyColumn {            // La columna scrollable
        items(itemHabitacion) { itemHabitacion ->
            Habitacion(hab = itemHabitacion, navController)         // Cada item de la lista es una habitación numerada
        }
    }
}

/**
 * Item de la lista, las habitaciones
 */
@Composable
fun Habitacion(hab: AlquilerEntity, navController: NavController) {
    Card(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(5.dp, color = Color.Black)
    ) {
        val fondo = if (hab.fechaEntrada > 0) Color.Red else Color.Green    // El fondo cambiará dependiendo de la fecha de entrada (0= hab.vacía)
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable {           // Si se pulsa la habitación...
                    navController.navigate("detalle/" + hab.id)     // ... se accederá a la pantalla 3 (detalle)
                }
                .background(fondo),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                text = hab.id.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
