package com.example.highscore

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.highscore.Entities.PodioEntity
import kotlinx.coroutines.flow.Flow


class PodioViewModel (application: Application):ViewModel() {

    companion object {
        lateinit var database: PodioDatabase
    }

}