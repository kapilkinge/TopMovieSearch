package com.app.topmoviesearch.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.topmoviesearch.model.Movies
import com.app.topmoviesearch.repository.MovieRepository
import com.app.topmoviesearch.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val movieRepository: MovieRepository) : ViewModel(){

    val movieLiveData : LiveData<Movies>
        get() = movieRepository.movies

    private var searchJob : Job? = null

    fun getMovies(searchText: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            movieRepository.getMovies(Constants.API_KEY, searchText)
        }
    }
}