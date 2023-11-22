package com.example.videos

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.videos.ui.theme.VideosTheme
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            videoPlayer(this)

        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun videoPlayer (context: Context) {

    //Esto es para un video de interne
    val videoURL= "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"

    val exoPlayer= SimpleExoPlayer.Builder(context).build()

    //ASociamos el playerView con el player.
    val playerView= PlayerView(context)

    val mediaItem= MediaItem.fromUri(videoURL)

    val playWhenReady by rememberSaveable{mutableStateOf(true)}

    //Hay que meter el mediaItem, la que tiene la URL del vídeo en el player.
    exoPlayer.setMediaItem(mediaItem)

    playerView.player=exoPlayer
    
    LaunchedEffect(exoPlayer) {

        //Preparamos el player.
       exoPlayer.prepare()

        exoPlayer.playWhenReady= playWhenReady
    }
    
    AndroidView(factory = {
        playerView
    })


}

