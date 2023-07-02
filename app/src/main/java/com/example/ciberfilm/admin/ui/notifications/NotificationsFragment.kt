package com.example.ciberfilm.admin.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ciberfilm.ViewDetailsMovie
import com.example.ciberfilm.ViewDetailsSale
//import com.example.ciberfilm.admin.databinding.FragmentNotificationsBinding
import com.example.ciberfilm.databinding.FragmentNotificationsBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var saleAdapter : SaleAdapter
    private var idSale: String = "0"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        GlobalScope.launch ( Dispatchers.Main ){
            val response = withContext(Dispatchers.IO){
                Api.build().getAllSales()
            }

            if(response.isSuccessful){
                val sales = response.body()
                sales?.let { salesAdll ->
                    saleAdapter.updateList(sales)
                }
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}