package com.example.rentingroom.ViewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.rentingroom.Room.RentingDao
import com.example.rentingroom.Room.RentingDatabase
import com.example.rentingroom.States.AlquilerState
import kotlinx.coroutines.launch

//Recibe el dao por parámetros.
class RentingVM (

    application: Application

    //Esta clase hereda de ViewModel
    //Es el encargado de conectar las vistas con el DAO.
):ViewModel() {

   val room: RentingDatabase by lazy {
       Room.databaseBuilder(application, RentingDatabase::class.java, "rentindb").build()
   }


}



