package com.example.ciberfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ciberfilm.R
import com.example.ciberfilm.databinding.ItemTiendaBinding
import com.example.ciberfilm.model.Tienda
import com.squareup.picasso.Picasso

class TiendaAdapter constructor(var tienda: List<Tienda> = listOf()) : RecyclerView.Adapter<TiendaAdapter.ViewHolder>(){

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemTiendaBinding = ItemTiendaBinding.bind(itemView)
        fun bind(tienda: Tienda) {

            binding.NombreTi.text = tienda.nombre
            binding.descri.text = tienda.descripcion
            binding.precio.append(tienda.precio.toString())

            Picasso.get().load(tienda.img).into(binding.img)
        }
    }
    fun updateList(tienda: List<Tienda>){
        this.tienda = tienda
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiendaAdapter.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_tienda, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TiendaAdapter.ViewHolder, position: Int) {
        val tienda = tienda[position]
        holder.bind(tienda)
    }

    override fun getItemCount(): Int {

        return tienda.size
    }


}