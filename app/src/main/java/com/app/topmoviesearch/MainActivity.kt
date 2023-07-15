package com.app.topmoviesearch

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.topmoviesearch.adapter.MovieListAdapter
import com.app.topmoviesearch.model.Movies
import com.app.topmoviesearch.model.Result
import com.app.topmoviesearch.utils.Constants
import com.app.topmoviesearch.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    val tvSearchMoviePlaceholder : TextView
        get() = findViewById(R.id.tvSearchMoviePlaceHolder)
    val progressBar : ProgressBar
        get() = findViewById(R.id.progressBar)
    val recyclerView : RecyclerView
        get() = findViewById(R.id.rvMovies)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mainViewModel.movieLiveData.observe(this, Observer {
            setupRecyclerView(it.results)
        })
    }

    fun setupRecyclerView(list : List<Result>) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MovieListAdapter(list)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchViewItem = menu?.findItem(R.id.search_bar)
        val searchView = searchViewItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search_movie)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.isBlank()) {
                        recyclerView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        tvSearchMoviePlaceholder.visibility = View.VISIBLE
                    } else {
                        progressBar.visibility = View.VISIBLE
                        tvSearchMoviePlaceholder.visibility = View.GONE
                        mainViewModel.getMovies(newText!!)
                    }
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}