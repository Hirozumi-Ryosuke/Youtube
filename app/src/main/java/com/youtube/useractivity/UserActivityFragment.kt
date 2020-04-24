package com.youtube.useractivity

import android.os.Bundle
import android.view.View
import com.youtube.R

class UserActivityFragment : BaseFragment() {

    companion object {
        val TAG = UserActivityFragment::class.java.simpleName
        fun newInstance() = UserActivityFragment()
    }

    override fun getLayoutId() = R.layout.fragment_trending

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLabel.text = "Your Activities"
    }
}