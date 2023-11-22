package com.example.juego

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.juego.ui.theme.JuegoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuegoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Greeting(modifier: Modifier = Modifier) {

    //Hacemos un contexto.
    val mContext = LocalContext.current

    Column (modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
        .background(Color.White)
        .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){

            Image(painter = painterResource(R.drawable.pato), contentDescription = "pato",
                modifier=Modifier.clickable {

                    //Hacemos un reproductor,
                    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.pato)
                    mMediaPlayer.start()


                })
            Image(painter = painterResource(R.drawable.gato), contentDescription = "gato",
                modifier=Modifier.clickable {

                    //Hacemos un reproductor,
                    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.gato)
                    mMediaPlayer.start()


                })

            Image(painter = painterResource(R.drawable.perro), contentDescription = "perro",
                modifier=Modifier.clickable {

                    //Hacemos un reproductor,
                    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.perro)

                    mMediaPlayer.start()


                })

            Image(painter = painterResource(R.drawable.vaca), contentDescription = "vaca",
                modifier=Modifier.clickable {

                    //Hacemos un reproductor,
                    val mMediaPlayer = MediaPlayer.create(mContext, R.raw.vaca)
                    mMediaPlayer.start()


                } )

            }
        }

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JuegoTheme {
        Greeting()
    }
}

