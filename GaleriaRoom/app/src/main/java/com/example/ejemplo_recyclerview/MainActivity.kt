package com.example.ejemplo_recyclerview

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo_recyclerview.databinding.ContactosBinding
import com.example.ejemplo_recyclerview.databinding.ItemContactoBinding
import com.example.ejemplo_recyclerview.databinding.NuevoContactoBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContactosAdapter
    lateinit var  listaContactos: MutableList<Contacto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vistaContactos=ContactosBinding.inflate(layoutInflater)
        val vistaNuevoContacto=NuevoContactoBinding.inflate(layoutInflater)

        //Cargamos la vista.
        setContentView(vistaContactos.root)

        //Preparamos la lista.
        listaContactos=ArrayList()

        //Cargamos la lista a través del DAO.
        getAllContactos()

        //Hacemos un botón.
        val btnNuevoContacto=vistaNuevoContacto.btnAddContacto

        btnNuevoContacto.setOnClickListener{
            //Cargamos la vista de nuevoContacto.
            setContentView(vistaNuevoContacto.root)
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
    private fun setUpRecyclerView(listaContactos: List<Contacto>) {

        //Asignamos el adaptador
        adapter= ContactosAdapter(listaContactos)

        val vistaContactos= ContactosBinding.inflate(layoutInflater)
        //ASignamos la vista
        recyclerView=vistaContactos.rvContacto
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(this)

        //Asignamos el adaptador a la vista.
        recyclerView.adapter=adapter

    }
}


