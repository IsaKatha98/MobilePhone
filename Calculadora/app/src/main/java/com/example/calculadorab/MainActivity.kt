package com.example.calculadorab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.calculadorab.databinding.ActivityMainBinding
import com.example.calculadorab.databinding.CalculadoraBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var nombre =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        Log.i("act", "on create")
    }

    override fun onResume() {
        super.onResume()


        val principal = ActivityMainBinding.inflate(layoutInflater)
        val calc = CalculadoraBinding.inflate(layoutInflater)

        setContentView(principal.root)

        var boton = principal.button
        var vuelta = calc.btnBack
        var suma = calc.btnSuma
        var resta = calc.btnResta
        var mult = calc.btnMult
        var div = calc.btnDiv

        var numUno = calc.numPrimerFact.text.toString()
        var numDos = calc.numSegundoFact.text.toString()


        //El botón que va del login hacia la calculadora
        boton.setOnClickListener {

            //Referenciamos a la base de datos.
            val user= principal.tvUsuario.toString()
            val password=principal.tvPass.toString()

            val db= FirebaseDatabase.getInstance().getReference("Calculos")
            val calculo=Calculo(user, password)
            db.child(user).setValue(calculo).addOnSuccessListener {

                Toast.makeText(this,"se ha gurdado",Toast.LENGTH_SHORT).show()
                //vista de la calculadora
                setContentView(calc.root)
            }.addOnFailureListener {
                Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show()
            }



        }

        //botón que va de la calculadora al login
        vuelta.setOnClickListener() {

            val toast = Toast.makeText(
                applicationContext,
                "Patrás",
                Toast.LENGTH_SHORT
            ).show()

            setContentView(principal.root)

        }

        //botón que suma
        suma.setOnClickListener() {
            numUno = calc.numPrimerFact.text.toString()
            numDos = calc.numSegundoFact.text.toString()
            var res = 0.0
            if (numUno.isEmpty() && numDos.isEmpty()) {

                calc.res.text = "0.0"
            } else {
                if (numUno.isEmpty()) {

                    Log.i("act", "el primer num era 0")
                    numUno = "0.0"


                } else if (numDos.isEmpty()) {

                    Log.i("act", "el segundo num era 0")

                    numDos = "0.0"


                }

                res = (numUno.toDouble()) + (numDos.toDouble())
                calc.res.text = "$res"

            }
        }

        //botón que resta
        resta.setOnClickListener() {
            var numUno = calc.numPrimerFact.text.toString()
            var numDos = calc.numSegundoFact.text.toString()
            var res = 0.0

            if (numUno.isEmpty() && numDos.isEmpty()) {

                calc.res.text = "0.0"
            } else {

                if (numUno.isEmpty()) {

                    Log.i("act", "el primer num era 0")
                    numUno = "0.0"


                } else if (numDos.isEmpty()) {

                    Log.i("act", "el segundo num era 0")

                    numDos = "0.0"

                }


                res = (numUno.toDouble()) - (numDos.toDouble())




                calc.res.text = "$res"

            }
        }

        //botón que multiplica
        mult.setOnClickListener() {
            var numUno = calc.numPrimerFact.text.toString()
            var numDos = calc.numSegundoFact.text.toString()
            var res = 0.0

            if (numUno.isEmpty() && numDos.isEmpty()) {

                calc.res.text = "0.0"
            } else {
                if (numUno.isEmpty()) {

                    Log.i("act", "el primer num era 0")
                    numUno = "0.0"


                } else if (numDos.isEmpty()) {

                    Log.i("act", "el segundo num era 0")

                    numDos = "0.0"


                }
                res = (numUno.toDouble()) * (numDos.toDouble())

                calc.res.text = "$res"

            }
        }

        //botón que divide
        div.setOnClickListener() {
            var numUno = calc.numPrimerFact.text.toString()
            var numDos = calc.numSegundoFact.text.toString()
            var res = 0.0
            if (numUno.isEmpty() && numDos.isEmpty()) {

                calc.res.text = "0.0"
            } else {
                if (numUno.isEmpty()) {

                    Log.i("act", "el primer num era 0")
                    numUno = "0.0"


                } else if (numDos.isEmpty() || numDos.equals("0")) {

                    val toast = Toast.makeText(
                        applicationContext,
                        "No se puede dividir entre 0",
                        Toast.LENGTH_SHORT
                    ).show()


                }

                res = (numUno.toDouble()) / (numDos.toDouble())

                calc.res.text = "$res"

            }
        }


    }

}
