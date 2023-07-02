package com.example.ciberfilm.admin.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ciberfilm.ViewDetailsActivity
import com.example.ciberfilm.admin.AddClienActivity
//import com.example.ciberfilm.admin.databinding.FragmentHomeBinding
import com.example.ciberfilm.databinding.FragmentHomeBinding
import com.example.ciberfilm.model.Customer
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var customerAdapter: CustomerAdapter
    private var idCustomer: String = "0"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        //bundle?.let {
        // idCustomer = it.getString("KEY_CUSTOMER_FRAG")?:"0"
        //}

        loadAdapter()
    }

    private fun loadAdapter() {
        customerAdapter = CustomerAdapter(onItemView = { customer ->
            val bundle = Bundle().apply {
                putString("KEY_ID", customer.id.toString())
                putString("KEY_EMAIL", customer.email)
                putString("KEY_FULLNAME", customer.fullName)
                putString("KEY_PASSWORD", customer.password)
                putString("KEY_STATUS", customer.status.toString())

            }

            val intent = Intent(context, ViewDetailsActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)

        })
        binding.rvCustomers.adapter = customerAdapter
    }

    override fun onStart() {
        super.onStart()
        loadList()
    }


    private fun loadList() {
        GlobalScope.launch(Dispatchers.Main) {

            val response = withContext(Dispatchers.IO) {
                Api.build().getAllCustomers()
            }

            if (response.isSuccessful) {
                val customers = response.body()
                customers?.let { customersAll ->
                    customerAdapter.updateList(customers)
                }

            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}