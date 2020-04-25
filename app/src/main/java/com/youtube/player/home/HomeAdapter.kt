package com.youtube.player.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.youtube.R
import com.youtube.player.data.local.Movies

class HomeAdapter(private val listener: ((Movies) -> Unit)? = null) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var moviesList = mutableListOf<Movies>()
        set(value) {
            field.clear()
            moviesList.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_home_feed, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount() = moviesList.count()

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: Movies) = with(itemView) {
            imgPoster.loadFromUrl(movies.poster)
            txtTitle.text = movies.title
            txtDescription.text = movies.description
            setOnClickListener {
                listener?.invoke(moviesList[layoutPosition])
            }
        }
    }
}