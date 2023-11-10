package com.example.ejemplo_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo_recyclerview.databinding.ItemContactoBinding

class ContactosAdapter (
    val contactos: List<Contacto>,

    ): RecyclerView.Adapter<ContactosAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemContactoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(contacto: Contacto) {

            binding.nombre.text=contacto.name

            binding.tlf.text=contacto.tlf

            //en caso de que el contacto sea mujer ponemos visible el icono
            if (contacto.gender=="mujer") {
                binding.fotoContacto.setImageResource(R.drawable.mujer)
            } else {
                binding.fotoContacto.setImageResource(R.drawable.hombre)
            }


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemContactoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, posicion: Int) {
        holder.bind(contactos[posicion])





    }
}
