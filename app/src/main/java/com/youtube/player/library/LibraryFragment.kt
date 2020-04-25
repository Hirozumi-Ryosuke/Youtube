package com.youtube.player.library

import android.os.Bundle
import android.view.View
import com.youtube.R
import com.youtube.player.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_trending.*

class LibraryFragment : BaseFragment() {

    companion object {
        val TAG = LibraryFragment::class.java.simpleName
        fun newInstance() = LibraryFragment()
    }

    override fun getLayoutId() = R.layout.fragment_trending

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLabel.text = "Videos Library"
    }
}