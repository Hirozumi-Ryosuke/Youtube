package com.youtube.animations

import android.view.GestureDetector
import android.view.MotionEvent

class GestureControl : GestureDetector.SimpleOnGestureListener() {
    override fun onSingleTapUp(event: MotionEvent): Boolean {
        return true
    }
}