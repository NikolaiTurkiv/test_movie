package com.example.movie.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MainInformation {
    @SerializedName("page")
    @Expose
    val page = 0

    @SerializedName("results")
    @Expose
    val results: List<ResultFilmData?>? = null

    @SerializedName("total_pages")
    @Expose
    val totalPages = 0

    @SerializedName("total_results")
    @Expose
    val totalResults = 0

}