package com.example.roomjetpack.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.roomjetpack.Entities.Users
import com.example.roomjetpack.Navigation.navManager
import com.example.roomjetpack.ViewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarView (navController: NavController, vm: UserVM) {

    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Agregar", color = Color.White, fontWeight = FontWeight.Bold)
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor= MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                //Este botón navega a la vista anterior
                IconButton(onClick = {navController.popBackStack()}
                ) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Patrás", tint = Color.White)

                }
            }
        )
    }

    ){
        ContentAgregarView(it, navController, vm)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentAgregarView (it:PaddingValues, navController: NavController, vm: UserVM) {

    var userName by remember {mutableStateOf("")}
    var password by remember {
        mutableStateOf("")
    }

    Column (modifier= Modifier
        .padding(it)
        .padding(top = 30.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        //Aquí es donde introduciremos el nombre de un user nuevo.
        OutlinedTextField(
            value = userName,
            onValueChange = {userName = it} ,

            )
        //Solo fufa si se coge el que acepta Textfield value, si coges el de String cagaste.
        OutlinedTextField(value = userName,
            onValueChange = {userName=it},
            label = { Text(text =  "user")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
            )

        OutlinedTextField(value = password,
            onValueChange = {password=it},
            label = { Text(text =  "password")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        //este botón va a agregar el usuario nuevo.
        Button(onClick = {
            val user= Users(userName=userName,password=password)

            vm.insert(user)

            //Nos volvemos a la vista inicio.
            navController.popBackStack()
        }) {
            Text(text = "Agregar")
        }
    }
}






