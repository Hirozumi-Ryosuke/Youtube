package com.youtube.player.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.youtube.R
import com.youtube.player.base.BaseFragment
import com.youtube.player.base.getActivityViewModel
import com.youtube.player.base.repo.Status
import com.youtube.player.base.widgets.SimpleDividerItemDecoration
import com.youtube.player.dashboard.DashboardViewModel
import com.youtube.player.timber.Timber
import kotlinx.android.synthetic.main.fragment_home.*

@Suppress("SYNTHETIC_UNRESOLVED_WIDGET_TYPE")
class HomeFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_home

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance() = HomeFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    private val homeAdapter = HomeAdapter {
        dashboardViewModel.loadVideo(it)
    }
    private lateinit var dashboardViewModel: DashboardViewModel

    @VisibleForTesting
    val countingIdleResources = CountingIdlingResource(TAG)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboardViewModel = getActivityViewModel()
        rvMovies.layoutManager = LinearLayoutManager(activity)
        rvMovies.addItemDecoration(SimpleDividerItemDecoration(context))
        rvMovies.adapter = homeAdapter

        countingIdleResources.increment()
        dashboardViewModel.movies.observe(this, Observer {
            when (it?.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        homeAdapter.moviesList = it.toMutableList()
                    }
                    countingIdleResources.decrement()
                }
                Status.ERROR -> {
                    countingIdleResources.decrement()
                }
                Status.LOADING -> {
                    Timber.e("Loading")
                    it.data?.let {
                        homeAdapter.moviesList = it.toMutableList()
                    }
                }
            }
        })
    }
}