package com.youtube.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var moviesRepository: MoviesRepository
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