package com.example.ciberfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ciberfilm.R
import com.example.ciberfilm.databinding.ItemCinesBinding
import com.example.ciberfilm.model.Cines
import com.squareup.picasso.Picasso


class CinesAdapter constructor(var cines: List<Cines> = listOf())
    : RecyclerView.Adapter<CinesAdapter.ViewHolder>(){

    /**/ inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding: ItemCinesBinding = ItemCinesBinding.bind(itemView)
        fun bind(cine: Cines) {
            binding.cedeCine.append( cine.sede)
            binding.capasCine.append("${cine.capasi}")
            binding.descriCine.append(cine.descrip)
            binding.horaCine.append("${cine.hora}")
            binding.estadoCine.append(cine.estado)
            Picasso.get().load(cine.url).into(binding.imgCine)
        }
    }

    fun updateList(cines: List<Cines>){
        this.cines = cines
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinesAdapter.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_cines, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cines.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cines = cines[position]
        holder.bind(cines)
    }



}