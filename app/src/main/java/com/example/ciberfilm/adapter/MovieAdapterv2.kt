package com.example.ciberfilm.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ciberfilm.R
import com.example.ciberfilm.databinding.ItemMovieBinding
import com.example.ciberfilm.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapterv2 constructor(var movies:List<Movie> = listOf(), val onItemImageMovie:(Movie)->Unit)
    :RecyclerView.Adapter<MovieAdapterv2.ViewHolder>(){


    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding : ItemMovieBinding = ItemMovieBinding.bind(itemView)
        fun bind(movie : Movie){
            binding.tvPrice.text = movie.unitPrice.toString()
            binding.elpRbCalificacion.rating = movie.rating.toFloat()
            binding.elpTvTitulo.text = movie.title
            binding.tvGenre.text = movie.genre
            binding.tvDescriptionMovie.text = movie.description

            Picasso.get().load(movie.imageUrl).into(binding.imgpelicu)
            binding.imgpelicu.setOnClickListener{
                //Al darle clic debe mandar a un registro de venta
                onItemImageMovie(movie)
            }

            
        }
    }


    fun updateList(movies:List<Movie>){
        this.movies = movies
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapterv2.ViewHolder {
       val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieAdapterv2.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}