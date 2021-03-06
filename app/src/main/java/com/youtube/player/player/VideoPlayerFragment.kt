package com.youtube.player.player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.transition.TransitionInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.youtube.R
import com.youtube.player.base.*
import com.youtube.player.dashboard.DashboardViewModel
import com.youtube.player.dashboard.ViewsEvents
import kotlinx.android.synthetic.main.fragment_player.*

class VideoPlayerFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_player

    companion object {
        val TAG = VideoPlayerFragment::class.java.simpleName
        private const val EXTRA_MOVIE_ID = "extra_movie_id"
        fun newInstance(movieID: Int) = VideoPlayerFragment().apply {
            arguments = bundleOf(
                EXTRA_MOVIE_ID to movieID
            )
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val decorView = activity?.window?.decorView
        decorView?.setOnSystemUiVisibilityChangeListener { visibility ->
            // Note that system bars will only be "visible" if none of the
            // LOW_PROFILE, HIDE_NAVIGATION, or FULLSCREEN flags are set.
            if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                // adjustments to your UI, such as showing the action bar or
                // other navigational controls.
                startTimer()
            } else {
                // adjustments to your UI, such as hiding the action bar or
                // other navigational controls.
                stopTimer()
                showControllers(false)
            }
        }
        retainInstance = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dashboardViewModel = getActivityViewModel()

        val movieID = arguments?.getInt(EXTRA_MOVIE_ID) ?: 0

        showControllers(false)

        dashboardViewModel.getMoviesDetails(movieID).observe(this, Observer {

            it?.data?.let {
                imgPoster.loadFromUrl(it.poster)

                videoPlayer.setVideoURI(it.videoUrl.toUri())

                videoPlayer.setOnPreparedListener {
                    videoPlayer.start()
                    imgPoster.isGone = true
                }
            }
        })

        videoPlayer.setOnCompletionListener {
            imgPlayPause.setImageResource(R.drawable.ic_pause_black_24dp)
            videoPlayer.start()
        }

        btnChild.setOnClickListener {
            toast("Video Clicked")
        }

        imgFullScreen.setOnClickListener {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        imgPlayPause.setOnClickListener {
            if (videoPlayer.isPlaying) {
                videoPlayer.pause()
                imgPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp)
            } else {
                imgPlayPause.setImageResource(R.drawable.ic_pause_black_24dp)
                videoPlayer.start()
            }
            showControllers(false)
        }

        dashboardViewModel.controllersListener.observe(this, Observer {
            when (it) {
                is ViewsEvents.SHOW -> showControllers(true)
                is ViewsEvents.HIDE -> showControllers(false)
                is ViewsEvents.CLICKED -> {
                    showControllers(!imgFullScreen.isVisible)
                    activity?.let {
                        if (!it.isPortrait()) {
                            enableFullScreen(true)
                        }
                    }
                }
                is ViewsEvents.LONGPRESS -> TODO()
                is ViewsEvents.NONE -> TODO()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
        if (videoPlayer != null) {
            videoPlayer.stopPlayback()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Timber.e("Fragment Config Changes")
        stopTimer()
        activity?.let {
            if (it.isPortrait()) {
                enableFullScreen(false)
            }
        }
    }

    /**
     * Toggle the visibility of controller
     */
    private fun showControllers(isShow: Boolean) {
        imgFullScreen.isGone = !isShow
        imgPlayPause.isGone = !isShow
    }

    private var countDownTimer: CountDownTimer? = null

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onFinish() {
                activity?.let {
                    //Toggle it when device is landscape mode
                    if (!it.isPortrait()) {
                        enableFullScreen(true)
                        showControllers(false)
                    }
                }
            }

            override fun onTick(millisUntilFinished: Long) {
            }

        }
        countDownTimer?.start()
    }

    private fun stopTimer() = countDownTimer?.cancel()
}