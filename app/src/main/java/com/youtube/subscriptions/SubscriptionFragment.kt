package com.youtube.subscriptions

import android.os.Bundle
import android.view.View
import com.youtube.R

class SubscriptionFragment : BaseFragment() {

    companion object {
        val TAG = SubscriptionFragment::class.java.simpleName

        fun newInstance() = SubscriptionFragment()
    }

    override fun getLayoutId() = R.layout.fragment_trending

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLabel.text = "Subscriptions"
    }
}