package com.example.movie.screens.databaseMovies

import com.example.movie.database.Movie
import com.example.movie.database.MovieDatabase

interface DatabaseListView {
    fun getDatabase(movies : ArrayList<Movie>)
}