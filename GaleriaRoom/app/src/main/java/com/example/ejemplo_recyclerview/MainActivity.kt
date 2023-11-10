package com.example.ejemplo_recyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.ejemplo_recyclerview.databinding.ContactosBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contactos= ContactosBinding.inflate(layoutInflater)

        setContentView(contactos.root)

        contactos.vistaContactos.adapter=ContactosAdapter(

            listOf(

                Contacto("Juan","12345", "hombre"),
                Contacto("María", "6784561523", "mujer"),
                Contacto("Raúl","644789456", "hombre"),
                Contacto("Ana", "693882147", "mujer"),

                Contacto("Juan","12345", "hombre"),
                Contacto("María", "6784561523", "mujer"),
                Contacto("Raúl","644789456", "hombre"),
                Contacto("Ana","693882147", "mujer"),

                Contacto("Juan","12345", "hombre"),
                Contacto("María", "6784561523", "mujer"),
                Contacto("Raúl","644789456", "hombre"),
                Contacto("Ana","693882147","mujer"),

                Contacto("Juan","12345", "hombre"),
                Contacto("María", "6784561523", "mujer"),
                Contacto("Raúl","644789456", "hombre"),
                Contacto("Ana","693882147", "mujer")
            ),

        )
    }
}