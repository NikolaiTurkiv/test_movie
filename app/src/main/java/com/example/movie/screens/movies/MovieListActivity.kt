package com.example.movie.screens.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.screens.databaseMovies.DatabaseActivity
import com.example.movie.R
import com.example.movie.adapter.MovieAdapter
import com.example.movie.screens.databaseMovies.DatabaseListPresenter.Companion.deleteMovie
import com.example.movie.screens.databaseMovies.DatabaseListPresenter.Companion.insertMovie
import com.example.movie.database.Movie
import com.example.movie.database.MovieDatabase
import com.example.movie.pojo.ResultFilmData
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MovieListActivity : AppCompatActivity(), MovieListView {

    private lateinit var adapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var navView: BottomNavigationView
    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieListPresenter: MovieListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle(R.string.home)
        navView = findViewById(R.id.nav_view)
        recyclerView = home_recyclerView
        adapter = MovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        movieDatabase = MovieDatabase.getDatabase(this)
        movieListPresenter = MovieListPresenter(this)

        adapter.onMovieClickListener = object : MovieAdapter.OnMovieLongClickListener {
            override fun onMovieLongClick(position: Int) {
                if (!adapter.movieInfoList[position].changeImage) {
                    adapter.movieInfoList[position].changeImage = true
                    insertMovie(
                        adapter.movieInfoList[position].originalTitle.toString(),
                        adapter.movieInfoList[position].posterPath.toString(), movieDatabase
                   )
                    Toast.makeText(applicationContext,"Movie was added in Favorite",Toast.LENGTH_LONG).show()
                } else {
                    deleteMovie(
                        movieDatabase, Movie(
                            adapter.movieInfoList[position].originalTitle.toString(),
                            adapter.movieInfoList[position].posterPath.toString()
                        )
                    )
                    adapter.movieInfoList[position].changeImage = false
                    Toast.makeText(applicationContext,"Movie was deleted from Favorite",Toast.LENGTH_LONG).show()
                }
                adapter.notifyDataSetChanged()
            }
        }
        navView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_favorite -> {
                    startActivity(Intent(this, DatabaseActivity::class.java))
                }
            }
        }
    }

    override fun onStart() {
        movieListPresenter.loadData()
        super.onStart()
    }

    override fun onDestroy() {
        movieListPresenter.dispose()
        super.onDestroy()
    }
    override fun showData(array: ArrayList<ResultFilmData>) {
        adapter.movieInfoList.addAll(array as ArrayList<ResultFilmData>)
        adapter.notifyDataSetChanged()
    }

    override fun showError() {
        Toast.makeText(applicationContext, "Download Error", Toast.LENGTH_LONG).show()
    }
}



