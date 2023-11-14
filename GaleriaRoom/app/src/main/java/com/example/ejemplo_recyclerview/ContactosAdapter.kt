package com.example.ejemplo_recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo_recyclerview.databinding.ItemContactoBinding

/**
 * Clase que contiene el viewHolder y su respectivo recyclerview
 */
class ContactosAdapter (
    val contactos: List<Contacto>,

    ): RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {

    /**
     * Muestra la vista y cada contacto.
     */
    override fun onBindViewHolder(holder: ViewHolder, posicion: Int) {

        val item= contactos[posicion]
        holder.bind(item)
    }

    /**
     * Contenedor de la vista (holder)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)                       // Se instancia el Layout para la vista
        return ViewHolder(layoutInflater.inflate(R.layout.item_contacto, parent, false))
    }


    override fun getItemCount(): Int {
        return contactos.size
    }


    /**
     * Clase que muestra el recyclerview.
     */
    class ViewHolder(val binding: View) : RecyclerView.ViewHolder(binding) {

        //instancia la vista del contacto
        var tvContacto=binding.findViewById<TextView>(R.id.nombre)
        var tlf=binding.findViewById<TextView>(R.id.tlf)
        var foto=binding.findViewById<ImageView>(R.id.fotoContacto)


        fun bind(contacto: Contacto) { //hace referencia a la entidad

            tvContacto.text=contacto.name
            tlf.text=contacto.tlf

            //en caso de que el contacto sea mujer ponemos visible el icono
            if (contacto.gender=="mujer") {
                foto.setImageResource(R.drawable.mujer)
            } else {
                foto.setImageResource(R.drawable.hombre)
            }


        }
    }







}
