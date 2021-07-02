package com.example.movie.screens.movies

import android.util.Log
import android.widget.Toast
import com.example.movie.api.RetrofitInstance
import com.example.movie.pojo.ResultFilmData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieListPresenter(var movieListView: MovieListView) {

    private lateinit var disposable : Disposable

    fun loadData() {
        for (n in 1..4){
             disposable = RetrofitInstance.instance.getFilmData(n)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieListView.showData(it.results as ArrayList<ResultFilmData>)
                }, {
                    movieListView.showError()
                })
        }
    }

    fun dispose(){
        disposable.dispose()
    }
}