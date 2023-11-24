package com.example.roomjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.roomjetpack.Entities.Users
import com.example.roomjetpack.Navigation.NavManager
import com.example.roomjetpack.Room.UsersDatabase
import com.example.roomjetpack.ViewModel.UserVM
import com.example.roomjetpack.Views.AgregarView
import com.example.roomjetpack.Views.EditarView
import com.example.roomjetpack.Views.InicioView
import com.example.roomjetpack.ui.theme.RoomJetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomJetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Construimos la base de datos
                    val database = Room.databaseBuilder(this, UsersDatabase::class.java, "db_usuarios").build()

                    //Instanciamos el dao (data access object)
                    val dao = database.usersDAO()

                    //Instanciamos el viewModel y le pasamos el dao por parámetros
                    val viewModel = UserVM(dao)


                }
            }
        }
    }


}


