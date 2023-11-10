package com.example.highscore

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.highscore.Entities.PodioEntity
import kotlinx.coroutines.flow.Flow


class PodioViewModel (application: Application):ViewModel() {

    //lazy significa que labase de datos se creará solo cuando sea necesario
    //y se almacenará en la variable database.
    val database: PodioDatabase by lazy {
        Room.databaseBuilder(
            application,
            PodioDatabase::class.java,
            "podioTabla"
        ).build()
    }
}