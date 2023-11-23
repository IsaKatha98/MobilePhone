package com.example.roomjetpack.Views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.roomjetpack.ViewModel.UserVM

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//La ? significa que pueden llegar vacíos.
fun EditarView (navController: NavController, vm: UserVM, id:Int, userName: String?, password:String?) {

    Scaffold (topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(text = "Editar", color = Color.White, fontWeight = FontWeight.Bold)
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
        ContentEditarView(it, navController, vm, id, userName, password)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentEditarView (it: PaddingValues, navController: NavController, vm: UserVM, id: Int, userName: String?, password: String?) {

    var userName by remember { mutableStateOf("") }
    var password by remember {
        mutableStateOf("")
    }

    Column (modifier= Modifier
        .padding(it)
        .padding(top = 30.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){

        //Aquí es donde introduciremos el nombre de un user nuevo.
        //Solo fufa si se coge el que acepta Textfield value, si coges el de String cagaste.
        //userName?:"" hace que si ese valor está vacío pues se muestra una cadena vacía.
        OutlinedTextField(value = userName?:"",
            onValueChange = {userName=it},
            label = { Text(text =  "user") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )

        OutlinedTextField(value = password?:"",
            onValueChange = {password=it},
            label = { Text(text =  "password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(bottom = 15.dp)
        )
        //este botón va a agregar el usuario nuevo.
        Button(onClick = {
            val user= Users(id=id, userName=userName!!,password=password!!)

            vm.update(user)

            //Nos volvemos a la vista inicio.
            navController.popBackStack()
        }) {
            Text(text = "Editar")
        }
    }
}
