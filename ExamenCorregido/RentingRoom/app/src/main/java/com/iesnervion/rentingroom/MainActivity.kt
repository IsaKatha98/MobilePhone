package com.iesnervion.rentingroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.iesnervion.rentingroom.crud.AlquilerDatabase
import com.iesnervion.rentingroom.ui.theme.RentingRoomTheme


class MainActivity : ComponentActivity() {
    // Global para toda la APP
    companion object {
        const val numHabitaciones: Int = 4          // Número de habitaciones
        const val precio: Float = 55.50f            // Precio de las habitaciones
        lateinit var database: AlquilerDatabase     // Base de datos (se inicializa al crear la APP)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializo la Base de datos
        database =
            Room.databaseBuilder(this, AlquilerDatabase::class.java, "alquileresDB").build()

        // Comienza JetPack Compose
        setContent {
            RentingRoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),              // A pantalla completa
                    color = MaterialTheme.colorScheme.background    // Uso el Schema de color
                ) {
                    val navController =
                        rememberNavController()     // NavController para los cambios de pantalla

                    // NavHost montará el Gráfico de navegación
                    NavHost(
                        navController = navController,
                        startDestination = "inicio"
                    ) {// Comienza en Inicio, la pantalla 1
                        composable("inicio") {
                            Pantalla1(navController = navController)
                        }
                        composable("lista") {   // Lista será la pantalla 2
                            Pantalla2(
                                navController = navController,
                            )
                        }
                        // La pantalla 3 será la que muestre el detalle de la habitación. Debo pasarle la ID pulsada
                        composable("detalle/{idHab}", arguments = listOf(
                            navArgument("idHab") { type = NavType.LongType }
                        )) { backStackEntry ->
                            Pantalla3(
                                navController = navController,
                                backStackEntry.arguments!!.getLong("idHab")
                            )
                        }
                    }
                }
            }
        }
    }

}
