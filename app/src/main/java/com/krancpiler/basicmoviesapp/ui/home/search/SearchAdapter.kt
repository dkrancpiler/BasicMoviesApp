package com.krancpiler.basicmoviesapp.ui.home.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.krancpiler.basicmoviesapp.BuildConfig
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.utility.getYearFromString

class SearchAdapter(diffCallback: DiffUtil.ItemCallback<MovieModel>): PagingDataAdapter<MovieModel, SearchAdapter.SearchViewHolder>(diffCallback) {

    var onItemClick: ((MovieModel) -> Unit)? = null

    class SearchViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val searchImage: AppCompatImageView = view.findViewById(R.id.search_image)
        val searchTitle: AppCompatTextView = view.findViewById(R.id.title_text)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movieModel = getItem(position)!!
        val date = movieModel.release_date.getYearFromString()
        val titleText = movieModel.title + " " + String.format(holder.itemView.resources.getString(R.string.surround_with_parentheses, date))
        holder.searchTitle.text = titleText
        holder.searchImage.setOnClickListener{
            onItemClick?.invoke(movieModel)
        }
        if (movieModel.poster_path != null) {
            Glide.with(holder.itemView.context)
                .load(BuildConfig.MOVIES_IMAGE_URL + movieModel.poster_path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.searchImage)
        } else {
            Glide.with(holder.itemView.context)
                .load(BuildConfig.EMPTY_IMAGE_URL)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.searchImage)
//            Glide.with(holder.itemView.context).clear(holder.searchImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(view)
    }

    object MovieComparator: DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }
}