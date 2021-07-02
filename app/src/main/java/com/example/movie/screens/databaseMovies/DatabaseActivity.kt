package com.example.movie.screens.databaseMovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.adapter.MovieDatabaseAdapter
import com.example.movie.screens.databaseMovies.DatabaseListPresenter.Companion.deleteMovie
import com.example.movie.database.Movie
import com.example.movie.database.MovieDatabase
import com.example.movie.screens.movies.MovieListActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_database.*

class DatabaseActivity : AppCompatActivity(),DatabaseListView {

    private lateinit var adapter: MovieDatabaseAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var navView: BottomNavigationView
    private lateinit var movieDatabase: MovieDatabase
    private lateinit var databaseListPresenter: DatabaseListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)
        setTitle(R.string.favorite)

        navView = findViewById(R.id.nav_view_favorite)
        recyclerView = recyclerViewFavorite
        adapter = MovieDatabaseAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        movieDatabase = MovieDatabase.getDatabase(this)

        databaseListPresenter = DatabaseListPresenter(this)
        databaseListPresenter.getDatabase(movieDatabase)

        adapter.onMovieDBClickListener = object : MovieDatabaseAdapter.OnMovieDBLongClickListener {
            override fun onMovieDBLongClick(position: Int) {
                deleteMovie(movieDatabase,adapter.movieDBInfoList[position])
                Toast.makeText(applicationContext,"Movie was deleted from Favorite", Toast.LENGTH_LONG).show()
                adapter.notifyDataSetChanged()
            }
        }
        navView.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MovieListActivity::class.java))
                }
            }
        }
    }

    override fun getDatabase(movies: ArrayList<Movie>) {
        adapter.movieDBInfoList = movies
        adapter.notifyDataSetChanged()
    }

}

