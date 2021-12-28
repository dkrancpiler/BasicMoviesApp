package com.krancpiler.basicmoviesapp.ui.home

import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.krancpiler.basicmoviesapp.BuildConfig
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.data.network.models.TrendingModel

class TrendingAdapter (private val data: ArrayList<TrendingModel>): RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    class TrendingViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<AppCompatImageView>(R.id.trending_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_trending, parent, false)
        return TrendingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        if (!data[position].poster_path.equals(null)) {
            Glide.with(holder.itemView.context)
                .load(BuildConfig.MOVIES_IMAGE_URL + data[position].poster_path)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView)
        } else {
            Glide.with(holder.itemView.context).clear(holder.imageView)
        }

    }

    override fun getItemCount() = data.size
}