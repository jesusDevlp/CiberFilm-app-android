package com.example.ciberfilm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.ciberfilm.admin.AdminActivity
import com.example.ciberfilm.databinding.ActivityLoginBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var txtUsuario: EditText
    private lateinit var txtPasword: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnCrear : Button





    fun initEvent(){
        val button = binding.btnIngresar
        button.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_login)
        txtUsuario = binding.txtUsuario
        txtPasword = binding.txtPasword
        btnIngresar = binding.btnIngresar
        btnCrear = binding.btnCrear




        btnIngresar.setOnClickListener {
            val email = binding.txtUsuario.text.toString()
            val password = binding.txtPasword.text.toString()


            GlobalScope.launch (Dispatchers.Main) {
                try {
                    //admin
                    binding.progressBar2.visibility = View.VISIBLE
                    if (email == "admin" && password == "admin123"){
                        val intent = Intent(this@LoginActivity,AdminActivity::class.java)
                        startActivity(intent)
                    }
                    val response = withContext(Dispatchers.IO){
                        Api.build().login(email, password)
                    }
                    if(response.isSuccessful){
                        val resultApiLogin = response.body()
                        resultApiLogin?.let {
                            showDialogMain("","Bienvenido")

                            val bundle = Bundle().apply{putString("KEY_CUSTOMER",it.id.toString())}
                            val intent = Intent(this@LoginActivity,HomeActivity::class.java).apply {putExtras(bundle) }
                            startActivity(intent)
                            return@launch
                        }
                    } else {
                       showDialogMain("¡AVISO!","Contraseña o usuario incorrectos")
                        return@launch
                    }

                } catch (ex:IOException) {
                    Toast.makeText(this@LoginActivity,"No tienes conexion", Toast.LENGTH_LONG).show()
                }catch (ex:Exception) {
                    Toast.makeText(this@LoginActivity,ex.message, Toast.LENGTH_LONG).show()
                } finally {
                    binding.progressBar2.visibility = View.GONE
                }
            }

        }

       btnCrear.setOnClickListener{
           val intent = Intent(this, CuentaActivity::class.java)
           startActivity(intent)
       }

    }

    private fun showDialogMain(title:String, msj:String){
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle(title)
        dialog.setMessage(msj)
        dialog.show()
    }
}