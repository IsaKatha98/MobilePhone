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
                    //Construimos la base de datos.
                   val database= Room.databaseBuilder(this, UsersDatabase::class.java, "users_database").build()

                    //Instanciamos el dao.
                    val dao=database.usersDAO()

                    //Instanciamos el viewModel.
                    val viewModel=UserVM(dao)

                    NavManager(viewModel=viewModel)
                }
            }
        }
    }

    class NavManager(viewModel: UserVM) {


        @Composable
//pasamos el vievModel por parametro.
        fun navManager(vm: UserVM) {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "inicio") {
                composable("inicio") {
                    InicioView(navController, vm)
                }
                composable("agregar") {
                    AgregarView(navController, vm)
                }
                //A esta vista hay que pasarle los datos del objeto, por lo que se lo pasamos
                //con el navController.
                composable(
                    "editar/{id}/{userName}/{password}", arguments = listOf(
                        navArgument("id") { type = NavType.IntType },
                        navArgument("userName") { type = NavType.StringType },
                        navArgument("password") { type = NavType.StringType },
                    )
                ) {

                    //Aparte del navController y el viewModel, hay que pasarle los datos a la vista.
                    //!!significa que no puede ser null ni vacío.
                    //? significa que puede ser null o estar vacío.
                    EditarView(
                        navController, vm,
                        it.arguments!!.getInt("id"),
                        it.arguments?.getString("userName"),
                        it.arguments?.getString("password")
                    )
                }
            }
        }
    }
}


