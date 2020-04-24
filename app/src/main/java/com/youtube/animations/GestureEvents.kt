package com.youtube.animations

import android.view.View

interface GestureEvents {
    fun onClick(view: View)
    fun onDismiss(view: View)
    fun onScale(percentage: Float)
    fun onSwipe(percentage: Float)
    fun onExpand(isExpanded: Boolean)
}