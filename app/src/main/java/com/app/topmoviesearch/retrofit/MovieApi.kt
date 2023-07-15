package com.app.topmoviesearch.retrofit

import com.app.topmoviesearch.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie")
    suspend fun getMovies(
        @Query("api_key") apiKey : String,
        @Query("query") searchText: String
    ) : Response<Movies>
}