package com.example.movie.screens.movies

import com.example.movie.pojo.ResultFilmData

interface MovieListView  {
    fun showData(array: ArrayList<ResultFilmData>)
    fun showError()
}