package com.example.movie.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

 class ResultFilmData {

    @SerializedName("adult")
    @Expose
    private var adult = false

    @SerializedName("backdrop_path")
    @Expose
    private var backdropPath: String? = null

    @SerializedName("genre_ids")
    @Expose
    private var genreIds: List<Int?>? = null

    @SerializedName("id")
    @Expose
    private var id = 0

    @SerializedName("original_language")
    @Expose
    private var originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
    val originalTitle: String? = null

    @SerializedName("overview")
    @Expose
    val overview: String? = null

    @SerializedName("popularity")
    @Expose
    private var popularity = 0.0

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    private var releaseDate: String? = null

    @SerializedName("title")
    @Expose
    private var title: String? = null

    @SerializedName("video")
    @Expose
    private var video = false

    @SerializedName("vote_average")
    @Expose
    private var voteAverage = 0.0

    @SerializedName("vote_count")
    @Expose
    private var voteCount = 0

    var changeImage = false


}