package com.example.ciberfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ciberfilm.databinding.ActivityViewDetailsBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.ResponseBody

class ViewDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewDetailsBinding
    private var idCustomer = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_view_details)
        binding = ActivityViewDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        //val datos = binding.txtDatos

        
        bundle?.let {
            val idCustomer = it.getString("KEY_ID") ?: ""
            val email = it.getString("KEY_EMAIL") ?: ""
            val fullName = it.getString("KEY_FULLNAME") ?: ""
            val password = it.getString("KEY_PASSWORD") ?: ""
            val status = it.getString("KEY_STATUS") ?: ""

            GlobalScope.launch (Dispatchers.Main){
                try {
                    val response = withContext(Dispatchers.IO){
                        Api.build().getFindByIdCustomer(idCustomer.toInt())
                    }
                    if(response.isSuccessful){
                        val customer = response.body()
                        customer?.let { obj->
                            binding.email.text = obj.email
                            binding.fullName.text= obj.fullName
                            binding.password.text = obj.password
                            binding.status.text= if(customer.status){
                                "Activo"
                            }else{
                                "Inactivo"
                            }
                        }
                    }
                } catch (ex:Exception){
                    Toast.makeText(this@ViewDetailsActivity,ex.message, Toast.LENGTH_LONG).show()
                }finally {

                }

            }

        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }

    }
}