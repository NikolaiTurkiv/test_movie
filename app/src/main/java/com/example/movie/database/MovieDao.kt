package com.example.movie.database

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getAllMovie() : Flowable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

}