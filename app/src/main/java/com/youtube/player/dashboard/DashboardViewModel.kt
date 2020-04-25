package com.youtube.player.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.youtube.player.PlayerApp
import com.youtube.player.base.liveUtils.SingleLiveEvent
import com.youtube.player.base.repo.Resource
import com.youtube.player.data.MoviesRepository
import com.youtube.player.data.local.Movies

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var moviesRepository: MoviesRepository

    annotation class Inject

    val movies: LiveData<Resource<List<Movies>>>
    val moviesSelectionListener = SingleLiveEvent<Movies>()
    val controllersListener = SingleLiveEvent<ViewsEvents>()

    private val viewClicked = ViewsEvents.CLICKED()
    private val viewShow = ViewsEvents.SHOW()
    private val viewHide = ViewsEvents.HIDE()
    private val viewLongPress = ViewsEvents.LONGPRESS()

    init {
        DaggerActivityComponent.builder()
            .baseNetworkComponent(PlayerApp.baseNetworkComponent)
            .build()
            .inject(this)

        movies = moviesRepository.getMoviesTrailers()
    }

    fun loadVideo(movies: Movies) {
        moviesSelectionListener.value = movies
    }

    fun showControllers(isShown: Boolean) {
        controllersListener.value = if (isShown) viewShow else viewHide
    }

    fun onClicked(isLongPress: Boolean = false) {
        controllersListener.value = if (isLongPress) viewLongPress else viewClicked
    }


    fun getMoviesDetails(movieId: Int) = moviesRepository.getMoviesDetails(movieId)
}