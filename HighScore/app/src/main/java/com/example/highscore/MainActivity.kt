package com.example.highscore

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
import com.example.highscore.ui.theme.HighScoreTheme
import android.R.attr.value
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.highscore.Entities.PodioEntity
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {

    val dao= PodioViewModel.database.podioDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       //No vamos a llamar a la tabla aquí porque está vacia.
        // Tenemos que añadirlo cuando alguien haga un login.
        PodioViewModel.database= Room.databaseBuilder(this, PodioDatabase::class.java,"podio-db").build()

        setContent {
            HighScoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                    ) {
                    navPantallas()
                }
            }
        }
    }

}

/**
 * Función que navega entre pantallas.
 */
@Composable
fun navPantallas() {

    val navController= rememberNavController()

    NavHost (navController=navController, startDestination="login") {

        //Pantalla del login
        composable("login") {login (navController=navController) }

        //Pantalla del juego
        composable("juego") { juego (navController=navController)}

        //Pantalla final
        composable ("podio"){podio(navController=navController)}
    }

}

/**
 * Función que diseña el login y llama al juego.
 * @param navController para cambiar de pantalla.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login( modifier: Modifier = Modifier, navController: NavController) {

    var userName by remember { mutableStateOf(TextFieldValue("Nombre de usuario: ")) }
    var password by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

        Image(
            painter = painterResource(R.drawable.inicio),
            contentDescription = "inicio"

        )

        TextField(
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            value = userName,
            onValueChange = { userName = it },

            //Asignamos el valor del label a la variable userName.
            label = { Text("$userName") }

        )

        TextField(
            modifier = Modifier
                .width(250.dp)
                .height(50.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña:") },
            visualTransformation = PasswordVisualTransformation(),
            //keyboardOptions = KeyboardOptions(keyboardType=KeyboardType.Password)

        )

        ElevatedButton(
            modifier=Modifier.padding(25.dp),
            onClick = { navController.navigate("juego")}) {

            Text("Go")

            //TODO: aquí debería comprobarse si el usuario existe, y si no, hacer un registro nuevo.

            //Creamos una entidad y le añadimos los datos
            //Llamamos a la función correspondiente.
            //Devuelve una entidad (objeto) que llamaremos jugador
           var jugador= nuevoJugador(entity = PodioEntity(), userName.toString())

        }
    }
}

/**
 * Función que diseña la interfaz.
 * @param navController para cambiar de pantalla.
 */
@Composable
fun juego(modifier: Modifier = Modifier, navController:NavController) {

    //Declaración de variables
    var jugador = remember { mutableStateOf("") }//Guarda la tirada del jugador
    var maquina = remember { mutableStateOf("") }//Guarda la tirada de la máquina
    var puntosJugador = remember { mutableStateOf(0) }//Guarda los puntos del jugador
    var puntosMaquina = remember { mutableStateOf(0) }//Guarda los puntos de la máquina
    val recuento =
        "${puntosJugador.value}-${puntosMaquina.value}"//Guarda el recuento de los puntos.

    //El context para el toast
    val context = LocalContext.current
    // Hacemos el toast.
    val toast: Toast = Toast.makeText(context, "undefined!", Toast.LENGTH_SHORT)

    //Empezamos con una columna principal que contendrá el resto de elementos.
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        //Fila que enseña el recuento total de las puntuaciones.
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1F, true)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly

        ) {
            Text(text = "$recuento", fontSize = 36.sp)
        }
        //Fila que indica los nombres de los jugadores.
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .weight(1F, true)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "$userName",
                    modifier = Modifier.padding(24.dp),
                    fontSize = 24.sp,
                    fontWeight = Bold
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Máquina",
                    modifier = Modifier.padding(24.dp),
                    fontSize = 24.sp,
                    fontWeight = Bold
                )
            }

        }
        //Fila que contiene el tablero del juego.
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .weight(2F, true)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //Hacemo una condición que se rompe en el momento que la puntuación o de la
            //máquina o del jugador llega a 5. Esto en realidad sería un bucle.
            if ( puntosMaquina.value < 3) {

                Column(modifier = modifier.weight(1F, true)) {
                    //Llama a la función que pinta la imagen del movimiento del jugador
                    movimiento(jugador.value)

                }
                Column(modifier = modifier.weight(1F, true)) {
                    //LLama a la función que pinta la imagen del movimiento de la máquina,
                    //Esta imagen dependerá de la función que randomiza el movimiento de la
                    //máquina.
                    movimiento(maquina.value)
                }
                //Cuando se rompe la condición, bloqueamos el tablero y preguntamos si quiere volver a jugar
            } else {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    //Se pone al ganador
                    if (puntosJugador.value > puntosMaquina.value) {
                        Text(text = "Ha ganado el jugador", fontSize = 24.sp)
                    } else if (puntosJugador.value < puntosMaquina.value) {
                        Text(text = "Ha ganado la máquina", fontSize = 24.sp)
                    }

                    //Botón que lleva a la pantalla final
                    ElevatedButton(

                        modifier=Modifier.padding(25.dp),
                        onClick = { navController.navigate("podio")}) {

                        Text("Resultados", fontSize = 30.sp, fontWeight = Bold)
                    }
                    //TODO: hay que bloquear los botones.

                }

            }
        }

        //Tenemos una fila con tres imágenes que hacen de botón.
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .height(70.dp)
                .weight(2F, true)
                .padding(0.dp, 0.dp, 0.dp, 15.dp),

            ) {
            Image(
                painter = painterResource(id = R.drawable.piedra),
                contentDescription = "piedra",
                modifier = Modifier
                    .weight(1F, true)
                    .clickable {
                        //Pone el movimiento del jugador a Piedra.
                        jugador.value = "Piedra"

                        //Llama a la función que devuelve un String con la tirada
                        //de la máquina.
                        maquina.value = tiradaMaquina()

                        //Hacemos el toast
                        if (jugador.value == maquina.value) {
                            toast.setText("Empate")

                        } else if (jugador.value == "Piedra" && maquina.value == "Papel") {
                            toast.setText("Ha ganado la máquina")
                            //Sumamos un punto a la máquina.
                            puntosMaquina.value++
                        } else if (jugador.value == "Piedra" && maquina.value == "Tijeras") {
                            toast.setText("Ha ganado el jugador")
                            //Sumamos un punto al jugador
                            puntosJugador.value++
                        }
                        toast.show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.papel),
                contentDescription = "papel",
                modifier = Modifier
                    .weight(1F, true)
                    .clickable {
                        //Pone el movimiento del jugador a Papel.
                        jugador.value = "Papel"

                        //Llama a la función que devuelve un String con la tirada
                        //de la máquina.
                        maquina.value = tiradaMaquina()

                        //Hacemos el toast
                        if (jugador.value == maquina.value) {
                            toast.setText("Empate")

                        } else if (jugador.value == "Papel" && maquina.value == "Tijeras") {
                            toast.setText("Ha ganado la máquina")
                            //Sumamos un punto a la máquina.
                            puntosMaquina.value++
                        } else if (jugador.value == "Papel" && maquina.value == "Piedra") {
                            toast.setText("Ha ganado el jugador")
                            //Sumamos un punto al jugador
                            puntosJugador.value++
                        }
                        toast.show()
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.tijeras),
                contentDescription = "tijeras",
                modifier = Modifier
                    .weight(1F, true)
                    .clickable {
                        //Pone el movimiento del jugador a Tijeras.
                        jugador.value = "Tijeras"

                        //Llama a la función que devuelve un String con la tirada
                        //de la máquina.
                        maquina.value = tiradaMaquina()

                        //Hacemos el toast
                        if (jugador.value == maquina.value) {
                            toast.setText("Empate")

                        } else if (jugador.value == "Tijeras" && maquina.value == "Piedra") {
                            toast.setText("Ha ganado la máquina")
                            //Sumamos un punto a la máquina.
                            puntosMaquina.value++
                        } else if (jugador.value == "Tijeras" && maquina.value == "Papel") {
                            toast.setText("Ha ganado el jugador")
                            //Sumamos un punto al jugador
                            puntosJugador.value++
                        }
                        toast.show()
                    }
            )

        }
    }
}

/**
 * Método que pinta la imagen según el movimiento del jugador o de la máquina.
 * Hace un switch según el string de entrada.
 * @param imagen cadena que indica de qué tipo es el movimiento
 */
@Composable
fun movimiento(imagen: String) {
    when (imagen) {
        "Piedra" -> Image(
            painter = painterResource(id = R.drawable.piedra),
            contentDescription = "Piedra"
        )

        "Papel" -> Image(
            painter = painterResource(id = R.drawable.papel),
            contentDescription = "Papel"
        )

        "Tijeras" -> Image(
            painter = painterResource(id = R.drawable.tijeras),
            contentDescription = "Tijeras"
        )
    }

}

/**
 * Función que elige por la máquina. Saca un valor aleatorio de una lista.
 * Según el index, se asigna uno de los valores de la lista.
 * @return devuelve un String con el movimiento de la máquina
 */
fun tiradaMaquina(): String {
    var movimiento = ""
    val list = listOf("Piedra", "Papel", "Tijeras")
    val index = Random.nextInt(list.size)
    movimiento = list[index]

    return movimiento
}

/**
 * Función que diseña la pantalla final.
 * @param navController para cambiar de pantalla.
 */
@Composable
fun podio (navController:NavController, String: userName) {

    //Declaración de variables

    var jugador = remember { mutableStateOf("") }//Guarda la tirada del jugador
    var maquina = remember { mutableStateOf("") }//Guarda la tirada de la máquina
    var puntosJugador = remember { mutableStateOf(0) }//Guarda los puntos del jugador
    var puntosMaquina = remember { mutableStateOf(0) }//Guarda los puntos de la máquina

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        //Hay que poner los valores a 0.

        Image(
            modifier=Modifier.weight(1F),
            painter = painterResource(R.drawable.podio),
            contentDescription = "podio",
        )

        //TODO: aquí van las cosas de la base de datos. Hay que insertar los valores de maxPuntuación y numPartidas.
        Column (modifier= Modifier.weight(2F)) {





           // actualizaPodio(entity,) {}

        }

        Row (modifier= Modifier.weight(1F)){
            //Botón de vuelta al juego.
            ElevatedButton(

                modifier= Modifier
                    .weight(1F),
                onClick = { navController.navigate("juego")}) {

                Text("Volver a jugar", fontSize = 30.sp)

                //Llamamos a la función nuevoJugador, para que actualize el numPartidas de
                //ese jugador.
                nuevoJugador(entity = PodioEntity(), userName.toString())

                //Ponemos los valores a 0.
                puntosJugador.value=0
                puntosMaquina.value=0
            }

            //Botón que lleva al login
            ElevatedButton(

                modifier= Modifier
                    .weight(1F),
                onClick = { navController.navigate("login")}) {

                Text("Salir", fontSize = 30.sp)

            }
        }

    }

}

/**
 * Función que comprueba si ese userName ya existen en la badat; y si no,
 * crea una nueva. Devuelve una entidad, vamos a hacer dos return pero gueno.
 */
fun nuevoJugador (nombre: String): PodioEntity {

    runBlocking {

        val dao= PodioViewModel.database.podioDao()

        val entity= dao.getPodioById(nombre)

       


        //Creamos un usuario nuevo.
        if (entity!=null) {

            entity.userName= nombre
            entity.maxPuntuacion=0
            entity.numPartidas=1

            dao.addPodio(entity)

        //Si ya existe ese jugador, actualizamos su contador de jugadas.
        } else {

            entity.numPartidas++

            dao.updatePodio(entity)
        }

    }

    return entity
}


/**
 * Función que actualiza la base de datos a través del dao.
 *
 * @param entity recibe una entidad (un objeto)
 * @param dao se conecta con el dao.
 */
fun actualizaPodio(entity: PodioEntity) {

    //Comprueba si ya existe el id y si la puntuación es mejor.
    //runBlocking es una corrutina que separa la ejecución de la app
    //de la interacción con la base de datos.
    runBlocking {

        val dao= PodioViewModel.database.podioDao()
        //Llamamos a una lista de puntuaciones.
        val puntuaciones=dao.getAllPodio()

        var existe= false //Variable que comprueba si el id ya existe
        var maxPuntuacion= false //Variable que comprueba si hay una puntuación mejor.

        for (puntuacion in puntuaciones) {
            if (puntuacion.id==entity.id) {
                existe=true

                if (puntuacion.maxPuntuacion<=entity.maxPuntuacion) {
                    maxPuntuacion=true
                }; break //detenemos el bucle for.
            }
        }

        //En caso de que haya modificado existe y maxPuntuación, actualizamos
        //la base de datos
        if (existe&&maxPuntuacion){
            val podio = PodioEntity(entity.id, entity.userName, entity.numPartidas, entity.maxPuntuacion)

            dao.updatePodio(podio)

        } else { //hacemos un insert en la base de datos
            val podio = PodioEntity(entity.id,entity.userName, entity.numPartidas, entity.maxPuntuacion)

            dao.addPodio(podio)
        }
    }
}


@Preview
@Composable
fun GreetingPreview() {
    HighScoreTheme {
        navPantallas()
    }
}


