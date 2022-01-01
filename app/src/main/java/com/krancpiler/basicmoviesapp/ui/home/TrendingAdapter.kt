package com.krancpiler.basicmoviesapp.ui.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.krancpiler.basicmoviesapp.BuildConfig
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.data.network.models.MovieModel
import com.krancpiler.basicmoviesapp.utility.getYearFromString

class TrendingAdapter (private val data: ArrayList<MovieModel>): RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    var onItemClick: ((MovieModel) -> Unit)? = null

    class TrendingViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val trendingImage: AppCompatImageView = view.findViewById(R.id.trending_image)
        val trendingTitle: AppCompatTextView = view.findViewById(R.id.title_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val movieModel = data[position]
        val date = movieModel.release_date.getYearFromString()
        val titleText = movieModel.title + " " + String.format(holder.itemView.resources.getString(R.string.surround_with_parentheses, date))
        holder.trendingTitle.text = titleText
        holder.trendingImage.setOnClickListener{
            onItemClick?.invoke(movieModel)
        }
        if (!movieModel.poster_path.equals(null)) {
            Glide.with(holder.itemView.context)
                .load(BuildConfig.MOVIES_IMAGE_URL + movieModel.poster_path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.trendingImage)
        } else {
            Glide.with(holder.itemView.context).clear(holder.trendingImage)
        }

    }

    override fun getItemCount() = data.size
}