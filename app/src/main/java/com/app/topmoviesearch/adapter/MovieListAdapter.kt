package com.app.topmoviesearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.topmoviesearch.R
import com.app.topmoviesearch.model.Result

class MovieListAdapter (private val list: List<Result>) :
    RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.textViewTitle.text = list[position].title
        holder.textViewReleaseDate.text = list[position].release_date
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewReleaseDate: TextView = itemView.findViewById(R.id.textViewReleaseDate)
    }

}