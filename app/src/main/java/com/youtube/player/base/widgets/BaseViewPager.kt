package com.youtube.player.base.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.annotation.Nullable
import androidx.viewpager.widget.ViewPager


class BaseViewPager : ViewPager {
    private var isSwipeEnabled = false

    constructor(context: Context) : super(context) {}
    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {}

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isSwipeEnabled && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isSwipeEnabled && super.onInterceptTouchEvent(ev)
    }

    /**
     * @param swipeEnabled True to enable the swipe gesture for the view pager to change the currently
     * displaying page. By default this gesture is enabled.
     */
    fun setSwipeEnabled(swipeEnabled: Boolean) {
        isSwipeEnabled = swipeEnabled
    }
}