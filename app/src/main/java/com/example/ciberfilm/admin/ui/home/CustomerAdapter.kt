package com.example.ciberfilm.admin.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.ciberfilm.R
import com.example.ciberfilm.databinding.ItemClienteBinding
import com.example.ciberfilm.model.Customer

class CustomerAdapter constructor (var customers:List<Customer> = listOf(), val onItemView:(Customer)->Unit) : RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    inner class ViewHolder constructor(itemView:View) : RecyclerView.ViewHolder(itemView){

        private val binding : ItemClienteBinding = ItemClienteBinding.bind(itemView)

        fun bind(customer:Customer){
            binding.Nom.text = customer.fullName
            binding.Email.text = customer.email
            binding.Claves.text = customer.password
            binding.EstaClien.text = if(customer.status){
                "Activo"
            }else{
                "Inactivo"
            }

            binding.imageView.setOnClickListener{
                onItemView(customer)
            }


        }


    }


    fun updateList(customers:List<Customer>){
        this.customers = customers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView:View = LayoutInflater.from(parent.context).inflate(R.layout.item_cliente,parent,false )
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
      return customers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val customer = customers[position]
        holder.bind(customer)
    }
}