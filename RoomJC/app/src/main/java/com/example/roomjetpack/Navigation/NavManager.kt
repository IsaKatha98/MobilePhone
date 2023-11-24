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
fun NavManager(viewModel: UserVM) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") {
            InicioView(navController, viewModel)
        }

        composable("agregar") {
            AgregarView(navController, viewModel)
        }

        composable("editar/{id}/{usuario}/{email}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("usuario") { type = NavType.StringType },
            navArgument("email") { type = NavType.StringType }

        )) {
            EditarView(navController, viewModel,
                it.arguments!!.getInt("id"),
                it.arguments?.getString("usuario"),
                it.arguments?.getString("email"))
        }
    }
}