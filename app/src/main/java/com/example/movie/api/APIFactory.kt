package com.example.movie.api

import com.example.movie.pojo.MainInformation
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface APIFactory {
    @GET("discover/movie?api_key=6f0d36f494a247a8f92abd16a269fc45&sort_by=popularity.desc")
    fun getFilmData(
        @Query("page") page : Int): Observable<MainInformation>

}