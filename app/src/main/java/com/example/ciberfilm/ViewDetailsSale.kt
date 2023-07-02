package com.example.ciberfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ciberfilm.databinding.ActivityViewDetailsMovieBinding
import com.example.ciberfilm.databinding.ActivityViewDetailsSaleBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewDetailsSale : AppCompatActivity() {
    private lateinit var binding : ActivityViewDetailsSaleBinding
    private var idSale: String = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDetailsSaleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras

        bundle?.let{
            val idSale = it.getString("KEY_SALE") ?: ""
            GlobalScope.launch (Dispatchers.Main){
                try {
                    val response = withContext(Dispatchers.IO){
                        Api.build().getFindByIdSale(idSale.toInt())
                    }

                    if(response.isSuccessful){
                        val sale = response.body()
                        sale?.let {obj ->
                            binding.operation.text = obj.operationNumber
                            binding.customer.text = obj.customerName
                            binding.tickets.text = "${obj.ticketsQuantity}"
                            binding.pay.text = obj.saleDate
                            binding.total.text = "${obj.total}"

                        }
                    }

                } catch (ex:Exception) {
                    Toast.makeText(this@ViewDetailsSale,ex.message, Toast.LENGTH_LONG).show()
                } finally {

                }
            }
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }
}