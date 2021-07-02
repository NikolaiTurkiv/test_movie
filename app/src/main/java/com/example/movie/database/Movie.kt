package com.example.movie.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
class Movie (
    @PrimaryKey
    @ColumnInfo(name = "title")val title: String,
    @ColumnInfo(name = "image_ref")val imageRef: String,
){
    var heartColor:Boolean = true
}