package com.example.ciberfilm.admin.ui.notifications

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.ciberfilm.R
import com.example.ciberfilm.databinding.ItemNotificationsBinding
import com.example.ciberfilm.model.Sales

class SaleAdapter constructor(
    var sales: List<Sales> = listOf(),
    val onItemImageView: (Sales) -> Unit
) :
    RecyclerView.Adapter<SaleAdapter.ViewHolder>() {
    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItemNotificationsBinding = ItemNotificationsBinding.bind(itemView)

        fun bind(sale: Sales) {
            binding.txtOperation.text = "${sale.operationNumber}"
            binding.txtCustomer.text = sale.customerName
            binding.txtTickets.text = "${sale.quantity}"
            binding.txtSaleDate.text = sale.saleDate
            binding.txtTotal.text = "${sale.total}"

            binding.imageView3.setOnClickListener {
                onItemImageView(sale)
            }
        }


    }

    fun updateList(sales: List<Sales>) {
        this.sales = sales
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_notifications, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return sales.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sale = sales[position]
        holder.bind(sale)
    }
}