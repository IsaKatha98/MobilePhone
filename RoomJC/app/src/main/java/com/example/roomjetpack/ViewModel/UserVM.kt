package com.example.roomjetpack.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomjetpack.Entities.Users
import com.example.roomjetpack.Room.UsersDao
import com.example.roomjetpack.States.UserState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class UserVM(

    //El viewmodel recibe el DAO.
    //Es el encargado de conectar las vistas con el DAO.
    private val dao:UsersDao

    //Esta clase hereda de ViewModel
):ViewModel() {

    var state by mutableStateOf(UserState())
        private set

    init {

        //Aquí llamamos a la función de usuarios que nos da va a dar una lista.
        //ViewModelScope.launch equivale a hacer un runBlocking.
        viewModelScope.launch {
            dao.getUsers().collectLatest {

                state= state.copy(listUsers=it)



            }

        }
    }

    fun insert(user:Users)=viewModelScope.launch {
        dao.insertUer(user=user)

    }

    fun update (user: Users)=viewModelScope.launch{
        dao.updateUser(user=user)

    }

    fun delete (user: Users)=viewModelScope.launch {
        dao.deleteUser(user=user )
    }
}