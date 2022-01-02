package com.krancpiler.basicmoviesapp.ui.home.singleMovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.krancpiler.basicmoviesapp.BuildConfig
import com.krancpiler.basicmoviesapp.R
import com.krancpiler.basicmoviesapp.data.network.models.SingleMovieDetailsResponse
import com.krancpiler.basicmoviesapp.databinding.FragmentSingleMovieBinding
import com.krancpiler.basicmoviesapp.ui.BaseFragment
import com.krancpiler.basicmoviesapp.ui.BaseViewModel
import com.krancpiler.basicmoviesapp.utility.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleMovieFragment : BaseFragment() {

    private lateinit var binding: FragmentSingleMovieBinding
    private val singleMovieViewModel: SingleMovieViewModel by viewModels()
    private val args: SingleMovieFragmentArgs by navArgs()

    override fun setUpErrorListening(viewModel: BaseViewModel) {
        singleMovieViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleMovieBinding.inflate(inflater, container, false)
        init()
        fetchData()
        return binding.root
    }

    private fun init() {
        changeToolbarTitle(args.movieName)
        showProgress()
        singleMovieViewModel.errorMessage.observe(viewLifecycleOwner, {
            if (it != null) {
                dismissProgress()
                showSimpleMessageDialog(it)
            }
        })
        singleMovieViewModel.singleMovieDetails.observe(viewLifecycleOwner, {
            dismissProgress()
            setUpLayout(it)
        })
        binding.reviewsSeeMore.setOnClickListener {
            showToast(resources.getString(R.string.not_yet_implemented))
        }
    }

    private fun setUpLayout(singleMovieDetailsResponse: SingleMovieDetailsResponse) {
        Glide.with(requireContext())
            .load(BuildConfig.MOVIES_IMAGE_URL + singleMovieDetailsResponse.backdrop_path)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.posterImage)
        binding.descriptionText.text = singleMovieDetailsResponse.overview
        if (singleMovieDetailsResponse.reviews.results.size != 0) {
            val review = singleMovieDetailsResponse.reviews.results[0]
            val rating = review.author_details.rating
            binding.reviewsTitle.text = String.format(
                resources.getString(
                    R.string.review_total_count,
                    singleMovieDetailsResponse.reviews.total_results.toString()
                )
            )
            binding.singleReviewItem.reviewAuthorName.text = String.format(
                resources.getString(
                    R.string.review_author_name_placeholder,
                    review.author
                )
            )
            if (rating != null) binding.singleReviewItem.reviewRatingText.text = String.format(
                resources.getString(
                    R.string.review_rating_placeholder,
                    rating.toString()
                )
            )
            else binding.singleReviewItem.reviewRatingText.text = String.format(
                resources.getString(
                    R.string.review_rating_placeholder,
                    "N/A"
                )
            )
            binding.singleReviewItem.reviewContent.text = review.content
        } else binding.reviewsLayout.visibility = View.GONE
    }

    private fun fetchData() {
        singleMovieViewModel.getSingleMovie(args.movieId)
    }
}