package com.example.roomjetpack.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.roomjetpack.ViewModel.UserVM
import com.example.roomjetpack.Views.AgregarView
import com.example.roomjetpack.Views.EditarView
import com.example.roomjetpack.Views.InicioView

@Composable
//pasamos el vievModel por parametro.
fun navManager(vm:UserVM){

    val navController= rememberNavController()

    NavHost(navController = navController, startDestination = "inicio" ) {
        composable("inicio"){
            InicioView(navController, vm)
        }
        composable("agregar"){
            AgregarView(navController, vm)
        }
        //A esta vista hay que pasarle los datos del objeto, por lo que se lo pasamos
        //con el navController.
        composable("editar/{id}/{userName}/{password}", arguments= listOf(
            navArgument("id"){type=NavType.IntType},
            navArgument("userName"){type=NavType.StringType},
            navArgument("password"){type=NavType.StringType},
            )){

            //Aparte del navController y el viewModel, hay que pasarle los datos a la vista.
            //!!significa que no puede ser null ni vacío.
            //? significa que puede ser null o estar vacío.
            EditarView(navController, vm,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("userName"),
                it.arguments?.getString("password"))
        }
    }
}