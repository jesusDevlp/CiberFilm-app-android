package com.example.ciberfilm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.ciberfilm.databinding.ActivityViewDetailsBinding
import com.example.ciberfilm.databinding.ActivityViewDetailsMovieBinding
import com.example.ciberfilm.networking.Api
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewDetailsMovie : AppCompatActivity() {
    private lateinit var binding : ActivityViewDetailsMovieBinding
    private var idMovie: String = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras


        bundle?.let {
            val idMovie = it.getString("KEY_MOVIE") ?: "0"

            GlobalScope.launch (Dispatchers.Main) {
                try {
                    val response = withContext(Dispatchers.IO){
                        Api.build().getFindByIdMovie(idMovie.toInt())
                    }

                    if(response.isSuccessful){
                        val movie = response.body()
                        movie?.let { obj ->

                            Picasso.get().load(movie.imageUrl).into(binding.imgView)
                            binding.tittle.text = obj.title
                            binding.gener.text = obj.genre
                            binding.description.text = obj.description
                            binding.duration.text= obj.status
                            binding.place.text = obj.timeEvent
                            binding.txtStatus.text = obj.place

                        }
                    }

                }catch (ex:Exception){
                    Toast.makeText(this@ViewDetailsMovie,ex.message, Toast.LENGTH_LONG).show()
                } finally {

                }
            }
        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }


    }
}