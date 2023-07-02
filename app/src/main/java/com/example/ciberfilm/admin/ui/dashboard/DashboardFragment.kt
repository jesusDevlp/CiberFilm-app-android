package com.example.ciberfilm.admin.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.ciberfilm.ViewDetailsMovie
//import com.example.ciberfilm.admin.databinding.FragmentDashboardBinding
import com.example.ciberfilm.databinding.FragmentDashboardBinding
import com.example.ciberfilm.networking.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var movieAdapter : MovieAdapter
    private var idMovie: String = "0"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadAdapter()



    }

    private fun loadAdapter(){
        movieAdapter = MovieAdapter(onItemImageView = {movie ->
            val bundle = Bundle().apply {
                putString("KEY_MOVIE",movie.id.toString())
            }

            val intent = Intent(context,ViewDetailsMovie::class.java).apply {
                putExtras(bundle)
            }
            startActivity(intent)

        })
        binding.rvMuvi.adapter = movieAdapter
    }


    override fun onStart() {
        super.onStart()
        loadList()
    }
    private fun loadList(){
        GlobalScope.launch (Dispatchers.Main){
            val response = withContext(Dispatchers.IO){
                Api.build().getAllMovies()
            }

            if(response.isSuccessful){
                val movies = response.body()
                movies?.let { moviesAll ->
                    movieAdapter.updateList(movies)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}