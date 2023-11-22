package com.example.ejercicio01_tema01

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.concat
import android.widget.Toast
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import androidx.core.app.NotificationCompat
import com.example.ejercicio01_tema01.databinding.ActivityMainBinding
import com.example.ejercicio01_tema01.databinding.BienvenidaBinding
import com.example.ejercicio01_tema01.databinding.PausaBinding


class MainActivity : AppCompatActivity() {

    var parar= false



    var nombre =""

    override fun onCreate(savedInstanceState: Bundle?) {

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val oBinding = BienvenidaBinding.inflate(layoutInflater)

        nombre = binding.tvUser.text.toString()
        super.onCreate(savedInstanceState)

        Log.i("act","on create")

    }

    override fun onStart () {

        super.onStart()

        Log.i("act","on start")

        val principal= BienvenidaBinding.inflate(layoutInflater)
        setContentView(principal.root)


    }

    override fun onResume() {

        super.onResume()

        Log.i("act","on resume")


        val binding = ActivityMainBinding.inflate(layoutInflater)
        val oBinding = BienvenidaBinding.inflate(layoutInflater)


        setContentView(binding.root)

        var boton = binding.button



        if (parar) {


            setContentView(oBinding.root)

            oBinding.textHola.text = "Nos alegramos de volver a verte, $nombre"

            parar=false


        } else {

            boton.setOnClickListener {

                val toast = Toast.makeText(
                    applicationContext,
                    "Me has pulsado",
                    Toast.LENGTH_SHORT
                ).show()

                setContentView(oBinding.root)

                oBinding.textHola.text = "Nos alegramos de volver a verte, $nombre"


                /*val intent = Intent(this , Bienvenida::class.java)
            intent.putExtra("usuario", binding.tvUser.text.toString())
            startActivity(intent)*/


            }
        }

        /*Log.i("act", "resuman")
        Toast.makeText(applicationContext, "Bienvenido de vuelta", Toast.LENGTH_SHORT).show()*/


    }
        override fun onPause() {
            super.onPause()

            Log.i("act", "onpause")
            Toast.makeText(applicationContext, "He parado", Toast.LENGTH_SHORT).show()

            val pausa = PausaBinding.inflate(layoutInflater)
            setContentView(pausa.root)

            parar=true




        }

    override fun onStop() {
        super.onStop()
        Log.i("actividad","on stop")

        onRestart()

    }

    override fun onRestart() {
        super.onRestart()

        Log.i("act", "onRestrart")

        Toast.makeText(applicationContext, "Bienvenido de vuelta", Toast.LENGTH_SHORT).show()

        val principal= BienvenidaBinding.inflate(layoutInflater)
        setContentView(principal.root)


    }

    override fun onDestroy() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "mi_canal_id"
            val channelName = "Mi Canal de Notificación"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        // Crea un intent para abrir una actividad cuando se toque la notificación
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // Crea la notificación
        val notificationBuilder = NotificationCompat.Builder(this, "mi_canal_id")
            .setSmallIcon(R.drawable.noti)
            .setContentTitle("Cierre de sesión")
            .setContentText("Se ha cerrado la sesión de $nombre")
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // Cierra la notificación al tocarla
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Muestra la notificación
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(1, notificationBuilder.build())

        super.onDestroy()
    }



}



           /* if(binding.tvUser.text.toString().isEmpty() || binding.tvUser.text.toString().isEmpty()){
                Toast.makeText(applicationContext, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this , Bienvenida::class.java)
                intent.putExtra("usuario", binding.tvUser.text.toString())
                startActivity(intent)
                Toast.makeText(applicationContext, "Iniciando sesión..." + binding.tvUser.text.toString(), Toast.LENGTH_SHORT).show()
            }

*/




