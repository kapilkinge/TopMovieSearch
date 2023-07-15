package com.app.topmoviesearch.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.topmoviesearch.model.Movies
import com.app.topmoviesearch.retrofit.MovieApi
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieApi: MovieApi) {

    private val _movies = MutableLiveData<Movies>()
    val movies : LiveData<Movies>
        get() = _movies

    suspend fun getMovies(apiKey: String, searchString: String) {
        val result = movieApi.getMovies(apiKey, searchString)
        if (result.isSuccessful && result.body() != null) {
            _movies.postValue(result.body())
        }
    }
}