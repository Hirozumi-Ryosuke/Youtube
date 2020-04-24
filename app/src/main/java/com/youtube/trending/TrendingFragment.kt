package com.youtube.trending

import android.os.Bundle
import android.view.View
import com.youtube.R

class TrendingFragment : BaseFragment() {

    companion object {
        val TAG = TrendingFragment::class.java.simpleName
        fun newInstance() = TrendingFragment()
    }

    override fun getLayoutId() = R.layout.fragment_trending

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLabel.text = "Trending Videos"
    }
}