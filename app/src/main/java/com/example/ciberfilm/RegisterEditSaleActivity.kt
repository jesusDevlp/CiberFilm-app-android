package com.example.ciberfilm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ciberfilm.databinding.ActivityRegisterEditSaleBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.squareup.picasso.Picasso
import androidx.appcompat.app.AlertDialog
import com.example.ciberfilm.model.SaleResquet

class RegisterEditSaleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterEditSaleBinding
    private var idCustomer = "1"
    private var idmovie = "0"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterEditSaleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_register_edit_sale)

        val bundle = intent.extras

        bundle?.let {
            idCustomer = it.getString("KEY_CUSTOMER")?:"1"
            idmovie = it.getString("KEY_MOVIE")?:"0"

            GlobalScope.launch (Dispatchers.Main) {
                try {
                    binding.progressBar.visibility = View.VISIBLE
                    val response = withContext(Dispatchers.IO){
                        Api.build().getFindByIdMovie(idmovie.toInt())
                    }
                    if(response.isSuccessful){
                        val movie = response.body()
                        movie?.let {obj ->
                            binding.tvTittle.text = obj.title
                            binding.tvDateEvent.text = "${obj.dateEvent} ${obj.timeEvent}"
                            binding.tvGenre.text = obj.genre
                            binding.tvDescription.text = obj.description
                            binding.tvPlace.text = obj.place
                            binding.tvPriceUnit.text = obj.unitPrice.toString()
                            binding.tvQuantity.text = obj.ticketsQuantity.toString()
                            Picasso.get().load(movie.imageUrl).into(binding.imgpelicu)
                        }
                    }
                } catch (ex:Exception) {
                    Toast.makeText(this@RegisterEditSaleActivity,ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        binding.btnRegister.setOnClickListener{
            var quantity = 0
            val title = "Advertencia"
            val msj = "El número de tickets ingresado no es valido"
            if(binding.editQuantity.text.toString() != "")
            {
                quantity = binding.editQuantity.text.toString().toInt()
                if(quantity==0)
                {
                    showDialogMain(title,msj)
                    return@setOnClickListener
                }
            }
            else{
                showDialogMain(title,msj)
                return@setOnClickListener
            }


            GlobalScope.launch (Dispatchers.Main) {
                try{
                    binding.progressBar.visibility = View.VISIBLE
                    val response = withContext(Dispatchers.IO){
                        Api.build().saveSale(request = SaleResquet(idmovie.toInt(),idCustomer.toInt(),quantity))
                    }
                    if(response.isSuccessful){
                        val result = response.body()
                        result?.let {
                            showDialogMain("Número de operación: "+result.id,result.message)
                        }
                    }
                }
                catch (exp : Exception){
                    println("Error de saveSale :"+exp.message)
                }
                finally {
                    binding.progressBar.visibility = View.GONE
                    binding.editQuantity.setText("")
                }
            }
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

    }

    private fun showDialogMain(title:String, msj:String){
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle(title)
        dialog.setMessage(msj)
        dialog.show()
    }
}