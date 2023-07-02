package com.example.ciberfilm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ciberfilm.admin.ui.notifications.SaleAdapter
import com.example.ciberfilm.databinding.ActivityHomeBinding
import com.example.ciberfilm.databinding.FragmentInicioBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InicioFragment : Fragment() {
    private var _binding : FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private lateinit var saleAdapter : SaleAdapter
    private var idCustomer : String = "1"


    private lateinit var bindingHome: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        bindingHome = ActivityHomeBinding.inflate(layoutInflater)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        bundle?.let {
            idCustomer = it.getString("KEY_CUSTOMER_FRAG")?:"1"
        }
        loadAdapter()
    }

    private fun loadAdapter(){
        saleAdapter = SaleAdapter(onItemImageView = {sales ->
            val bundle = Bundle().apply {
                putString("KEY_SALE",sales.id.toString())
            }
            val intent = Intent(context, ViewDetailsSale::class.java).apply{
                putExtras(bundle)
            }
            startActivity(intent)
        })
        binding.rvSales.adapter = saleAdapter
    }

    override fun onStart() {
        super.onStart()
        loadList()
    }

    private fun loadList(){
        GlobalScope.launch (Dispatchers.Main){
            binding.progressBar.visibility = View.VISIBLE
            val response = withContext(Dispatchers.IO){
                Api.build().getAllSalesById(idCustomer.toInt())
            }
            if (response.isSuccessful){
                val sales = response.body()
                sales?.let {
                    saleAdapter.updateList(sales)
                }
            }
            binding.progressBar.visibility = View.GONE
        }
    }


}