package com.iesnervion.rentingroom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

/**
 * Muestro la barra inferior con iconos para el acceso a las pantallas 1 y 2
 */
@Composable
fun BarraInferior(navController: NavController, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate("inicio")}) {
            Icon(Icons.Rounded.Home, contentDescription = "Configuración")
        }
        Button(onClick = { navController.navigate("lista")}) {
            Icon(Icons.Rounded.List, contentDescription = "Lista")
        }
    }
}