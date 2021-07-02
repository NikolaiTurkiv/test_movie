package com.example.movie.screens.databaseMovies

import android.util.Log
import android.widget.Toast
import com.example.movie.database.Movie
import com.example.movie.database.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.schedulers.Schedulers

class DatabaseListPresenter(var databaseListView: DatabaseListView) {

    companion object {

        fun deleteMovie(movieDatabase: MovieDatabase, movie: Movie) {
            Completable.fromAction(Action {
                movieDatabase.movieDao().deleteMovie(movie)
            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe({

                }, {
                    Log.d("RX3DELETE", it.message.toString())
                })
        }

        fun insertMovie(title: String, reference: String, movieDatabase: MovieDatabase) {
            val movie = Movie(title, reference)
            Observable.just(movie)
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                    {
                        movieDatabase.movieDao().addMovie(movie)
                        Log.d("RX3", it.imageRef)
                    }, {
                        Log.d("RX3", it.message.toString())
                    }
                )
        }

    }

    fun getDatabase(movieDatabase: MovieDatabase){
        movieDatabase.movieDao().getAllMovie().observeOn(AndroidSchedulers.mainThread()).subscribe {
            databaseListView.getDatabase(it as ArrayList<Movie>)
        }
    }

}