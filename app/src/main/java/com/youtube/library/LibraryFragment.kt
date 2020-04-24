package com.youtube.library

import android.os.Bundle
import android.view.View
import com.youtube.R

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