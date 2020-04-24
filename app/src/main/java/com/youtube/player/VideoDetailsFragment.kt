package com.youtube.player

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import com.youtube.R
import com.youtube.dashboard.DashboardViewModel

class VideoDetailsFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_video_details


    private lateinit var dashBoardViewModel: DashboardViewModel

    companion object {
        val TAG = VideoPlayerFragment::class.java.simpleName
        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun newInstance(movieId: Int) = VideoDetailsFragment().apply {
            arguments = bundleOf(
                EXTRA_MOVIE_ID to movieId
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        dashBoardViewModel = getActivityViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieID = arguments?.getInt(EXTRA_MOVIE_ID)!!
//        txtDescription.movementMethod = ScrollingMovementMethod()
        dashBoardViewModel.getMoviesDetails(movieID).observe(this, Observer {
            it?.data?.let {
                txtTitle.text = it.title
                txtYear.text = it.year.toString()
                txtDuration.text = it.duration
                txtGenre.text = it.genre
                txtDescription.text = it.description
            }
        })
    }
}