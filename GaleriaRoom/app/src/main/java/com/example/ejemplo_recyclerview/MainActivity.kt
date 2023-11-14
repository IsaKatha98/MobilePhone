package com.example.ejemplo_recyclerview

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContactosAdapter
    lateinit var  listaContactos: MutableList<Contacto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Cargamos la vista que añade contactos.
        setContentView(R.layout.contactos)

        //Preparamos la lista.
        listaContactos=ArrayList()

        //Cargamos la lista a través del DAO.
        getAllContactos()

        //Si clicamos en el botón.
        findViewById<Button>(R.id.btnAddContacto).setOnClickListener(

            //Llamamos a la función que hace las cosas del boton.
            cosasBoton()


        )
    }

    private fun cosasBoton() {

        //Cargamos la vista de nuevoContacto.
        setContentView(R.layout.nuevo_contacto)

        //Llamamos a la función que llama a la de añadir contactos del DAO.
        //Le pasamos por parámetro por el nombre, el tlf, y el género.
        addContacto(Contacto(
           // nombre=findViewById<EditText>(R.id.etNombreContacto).text.toString()))

    }

    private fun addContacto(contacto: Contacto)= runBlocking {

        launch {

            var nombre= findViewById<EditText>(R.id.etNombreContacto).text.toString()
            var tlf= findViewById<EditText>(R.id.etTlf).text.toString()
            var genero= findViewById<EditText>(R.id.etGenero).text.toString()

            //Pasamos el nombre como primary Key para indicar que añadimos un contacto nuevo
            nombre= MisContactosApp.database.contactosDao().addContacto(contacto)
            val recoveryContacto = MisContactosApp.database.contactosDao().getContactobyName(nombre)   // Recarga la lista

            listaContactos.add(recoveryContacto) 
        }


    }

    fun clearFocus(){
        findViewById<EditText>(R.id.etNombreContacto).setText("") // Borra el texto en el EditText
        findViewById<EditText>(R.id.etTlf).setText("") // Borra el texto en el EditText
        findViewById<EditText>(R.id.etGenero).setText("") // Borra el texto en el EditText
    }

    /**
     * Corrutina que saca de la base de datos la lista de contactos.
     */
    private fun getAllContactos() = runBlocking{

        launch{
            //Llamamos a la función del DAO y la guardamos en la vista.
            listaContactos=MisContactosApp.database.contactosDao().getAllContactos()

            //Se llama a la función que genera la vista y pasa la lista de contactos
            //como parámetro de entrada.
            setUpRecyclerView(listaContactos)
        }
    }

    /**
     * Función que muestra la vista del recyclerView
     */
    private fun setUpRecyclerView(listaContactos: MutableList<Contacto>) {

        //Asignamos el adaptador
        adapter= ContactosAdapter(listaContactos)

        //ASignamos la vista
        recyclerView=findViewById(R.id.vistaContactos)
        recyclerView.setHasFixedSize(true)

        //Asignamos el adaptador a la vista.
        recyclerView.adapter=adapter

    }
}