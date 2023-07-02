package com.example.ciberfilm

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ciberfilm.adapter.MovieAdapterv2
import com.example.ciberfilm.databinding.FragmentPeliculasBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PeliculasFragment : Fragment() {
    private var _binding : com.example.ciberfilm.databinding.FragmentPeliculasBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter : MovieAdapterv2
    private var idCustomer : String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPeliculasBinding.inflate(inflater, container, false)
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
        movieAdapter = MovieAdapterv2(onItemImageMovie = {movie ->
            val bundle = Bundle().apply {
                putString("KEY_MOVIE",movie.id.toString())
                putString("KEY_CUSTOMER",idCustomer)
            }
            val intent = Intent(context,RegisterEditSaleActivity::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)
        })
        binding.rvMovies.adapter = movieAdapter
    }

    override fun onStart() {
        super.onStart()
        loadList()
    }

    private fun loadList(){
        GlobalScope.launch (Dispatchers.Main){
            binding.progressBar.visibility = View.VISIBLE
            val response = withContext(Dispatchers.IO){
                Api.build().getAllMovies()
            }
            if (response.isSuccessful){
                val movies = response.body()
                movies?.let {
                    movieAdapter.updateList(movies)
                }
            }
            binding.progressBar.visibility = View.GONE
        }
    }
}
