package com.example.ejercicio01_tema01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.text.TextUtils.concat
import android.widget.Toast
import android.view.View
import com.example.ejercicio01_tema01.databinding.ActivityMainBinding
import com.example.ejercicio01_tema01.databinding.BienvenidaBinding


class Bienvenida: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding= BienvenidaBinding.inflate(layoutInflater)


        val usuario = intent.getStringExtra("usuario").toString()
        Log.i("actividad", "$usuario")

        binding.textHola.text = "¡Bienvenido $usuario!"
    }

    override fun onPause() {
        Log.i("actividad", "pausen")
        val binding = BienvenidaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.visibility = View.VISIBLE
        super.onPause()
    }

    override fun onResume() {
        Log.i("actividad", "resuman")
        Toast.makeText(applicationContext, "Bienvenido de vuelta", Toast.LENGTH_SHORT).show()
        super.onResume()
    }

}