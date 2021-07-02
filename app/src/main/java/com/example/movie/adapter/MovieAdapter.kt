package com.example.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.pojo.ResultFilmData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_adapter.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieInfoList: ArrayList<ResultFilmData> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    var onMovieClickListener : OnMovieLongClickListener? = null

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieLabelImage: ImageView = itemView.movieLabelImageView
        val movieTitle: TextView = itemView.movieTitleTextView
        val heart: ImageView = itemView.heartImageView
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_adapter, parent, false)
        return MovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieInfo = movieInfoList[position]
        with(holder) {
            movieTitle.text = movieInfo.originalTitle
            Picasso.get().load(BASE_URL_IMAGE + movieInfo.posterPath).into(movieLabelImage)
            if(!movieInfo.changeImage){
                heart.setImageResource(R.drawable.black_heart)
            }else{
                heart.setImageResource(R.drawable.red_heart)
            }
            itemView.setOnLongClickListener {
                onMovieClickListener?.onMovieLongClick(position)
                it.isClickable
            }

        }
    }

    override fun getItemCount(): Int {
        return movieInfoList.size
    }

    private val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500"

    interface OnMovieLongClickListener {
        fun onMovieLongClick(position: Int)
    }
    }

