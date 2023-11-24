package com.example.roomjetpack.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomjetpack.ViewModel.UserVM

//Recibe por parámetros el navController para poder navegar entre las vistas.
//y el viewModel para poder conectarse con el DAO.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioView(navController: NavController, vm: UserVM){

    //Vista predeterminada, esto se puede hacer con un modifier.
    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Inicio", color = Color.White, fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor= MaterialTheme.colorScheme.primary
            )
        )
    },
        //Esto simplemente es un botón flotante que usa el navController para ir a otra vista.
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate("agregar") },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                //Esto es el icono del botón.
                Icon(imageVector= Icons.Default.Add, contentDescription="Agregar")

            }
        }

    ) {

        ContentInicioView(it, navController, vm)

    }
}

@Composable
fun ContentInicioView(it:PaddingValues, navController: NavController, vm: UserVM) {

    val state=vm.state

    Column (modifier = Modifier.padding(it)){

        //Hacemos una lazyColumn porque no sabemos de qué tamaño va a ser esto.
        //En los items de la columna ponemos la lista de usuarios.
        LazyColumn {
            items(state.listUsers) {
                Box( modifier = Modifier
                    .padding(.8.dp)
                    .fillMaxWidth()
                    )

                //Aquí mostramos cada elemento de nuestro objeto, con su correspondiente boton de editar y de borrar.
                //Cuando clikamos en el boton viajamos a la vista editar con todos los elementos del objeto.
                    {Column (modifier=Modifier.padding(.12.dp)) {
                        Text(text = it.userName)
                        Text(text = it.password)
                        IconButton(onClick = {navController.navigate("editar/${it.id}/${it.userName}/${it.password}") }) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Editar")

                        }

                        IconButton(onClick = {vm.delete(it) }) {
                            Icon(imageVector = Icons.Default.Delete, contentDescription = "Borrar")

                        }

                    }
                }

            }
        }

    }





}