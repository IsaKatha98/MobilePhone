package com.example.rentingroom

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rentingroom.Entities.Configuracion
import com.example.rentingroom.ViewModel.RentingVM
import com.example.rentingroom.ui.theme.RentingRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RentingRoomTheme {

                val navController= rememberNavController()
                val context= LocalContext.current
                val database= RentingVM(context.applicationContext as Application).room

                NavHost(navController = navController, startDestination = "Configuracion" ) {
                    composable("Configuración") {
                        Configuracion(navController, database.RentingDao(), context)
                    }
                    composable("ListaHabitaciones") {
                        ListaHabitaciones(navController, database.RentingDao(), context)
                    }
                }


            }
        }
    }
}
