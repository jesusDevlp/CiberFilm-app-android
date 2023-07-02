package com.example.ciberfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ciberfilm.databinding.ActivityCuentaBinding
import com.example.ciberfilm.model.RegisterCustomerRequest
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class CuentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_cuenta)

        binding.btnRegistrar.setOnClickListener{
            val email = binding.txtEmail.text.toString()
            val name = binding.txtNombres.text.toString()
            val password = binding.txtContraseA.text.toString()

            GlobalScope.launch (Dispatchers.Main){
                try {
                    binding.progressBar.visibility = View.GONE
                   val response = withContext(Dispatchers.IO){
                        Api.build().saveCustomer(request = RegisterCustomerRequest(email,name,password))
                    }

                    if(response.isSuccessful){
                       val resultBody =  response.body()
                        resultBody?.let {
                            showDialogMain("Cuenta Creada con Exito!" + resultBody.id,resultBody.message)

                        }
                    }

                } catch (ex:IOException){
                    Toast.makeText(this@CuentaActivity, "No tienes conexion", Toast.LENGTH_SHORT).show()
                } catch (ex:java.lang.Exception){
                    Toast.makeText(this@CuentaActivity,ex.message, Toast.LENGTH_SHORT).show()
                } finally {
                    binding.txtEmail.setText("")
                    binding.txtNombres.setText("")
                    binding.txtContraseA.setText("")
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