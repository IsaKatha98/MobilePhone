package com.example.whatsappfirebase.Entities

import java.sql.Timestamp

data class Mensaje(
    val idMensaje: Int?=null,
    val mensaje:String?=null,
    val horaFecha: Timestamp?=null,
    val idSender: Int?=null,
    val idReceiver:Int?=null,
    val recibido:Boolean?=null,
    val visto:Boolean?=null

)
