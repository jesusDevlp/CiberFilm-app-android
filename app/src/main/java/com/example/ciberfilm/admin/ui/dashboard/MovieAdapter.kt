package com.example.ciberfilm.admin.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.ciberfilm.R
import com.example.ciberfilm.databinding.ItempeliBinding
import com.example.ciberfilm.model.Movie
import com.squareup.picasso.Picasso

class MovieAdapter constructor(
    var movies: List<Movie> = listOf(),
    val onItemImageView: (Movie) -> Unit
) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding: ItempeliBinding = ItempeliBinding.bind(itemView)
        fun bind(movie: Movie) {
            binding.title.text = movie.title
            binding.gene.text = movie.genre
            binding.descri.text = movie.description
            binding.durity.text = "${movie.timeEvent}"
            binding.lugar.text = movie.place
            binding.esta.text = movie.status
            Picasso.get().load(movie.imageUrl).into(binding.imgpelicu)

            binding.view.setOnClickListener {
                onItemImageView(movie)
            }


        }

    }

    fun updateList(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.itempeli, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }
}