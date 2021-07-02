package com.example.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.database.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.database_adapter.view.*

class MovieDatabaseAdapter : RecyclerView.Adapter<MovieDatabaseAdapter.MovieDataBaseViewHolder>() {

    var movieDBInfoList: ArrayList<Movie> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"
    var onMovieDBClickListener : MovieDatabaseAdapter.OnMovieDBLongClickListener? = null


    inner class MovieDataBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val movieLabelImage: ImageView = itemView.dbMovieLabelImageView
        val movieTitle: TextView = itemView.dbMovieTitleTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataBaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.database_adapter, parent, false)
        return MovieDataBaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieDataBaseViewHolder, position: Int) {
        val movieInfo = movieDBInfoList[position]
        with(holder) {
            movieTitle.text = movieInfo.title
            Picasso.get().load(BASE_URL_IMAGE + movieInfo.imageRef).into(movieLabelImage)
            itemView.setOnLongClickListener {
                onMovieDBClickListener?.onMovieDBLongClick(position)
                it.isClickable }
        }
    }

    override fun getItemCount(): Int {
      return  movieDBInfoList.size
    }

    interface OnMovieDBLongClickListener {
        fun onMovieDBLongClick(position: Int)
    }

}